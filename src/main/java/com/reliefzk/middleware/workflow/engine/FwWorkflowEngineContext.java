package com.reliefzk.middleware.workflow.engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.reliefzk.middleware.workflow.core.definations.FwWorkflow;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 工作流引擎context
 * @author kui.zhouk
 */
public class FwWorkflowEngineContext implements ApplicationContextAware {

    /**
     * 工作流map
     */
    private Map<String, Map<String, List<FwWorkflow.GateWay>>> workflowFrompMap = new ConcurrentHashMap<>();

    public boolean exists(String workflowId){
        return workflowFrompMap.containsKey(workflowId);
    }

    public List<FwWorkflow.GateWay> queryGateways(String workflowName, String from){
        if(exists(workflowName)){
            return workflowFrompMap.get(workflowName).get(from);
        }
        return new ArrayList<>();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, FwWorkflow> dataMap = applicationContext.getBeansOfType(FwWorkflow.class);
        for(FwWorkflow amlWorkflow : dataMap.values()) {
            /* workflowName: <fromStep: gateways> */
            Map<String, List<FwWorkflow.GateWay>> gatewayMap = workflowFrompMap.get(amlWorkflow.getId());
            if(gatewayMap == null) {
                gatewayMap = new HashMap<>();
            }
            /* fromStep: gateways */
            for(FwWorkflow.GateWay gateWay : amlWorkflow.getGateWays()){
                List<FwWorkflow.GateWay> gatewayList = gatewayMap.get(gateWay.getFrom());
                if(gatewayList == null) {
                    gatewayList = new ArrayList<>();
                }
                gatewayList.add(gateWay);

                gatewayMap.put(gateWay.getFrom(), gatewayList);
            }
            workflowFrompMap.put(amlWorkflow.getId(), gatewayMap);
        }
    }
}