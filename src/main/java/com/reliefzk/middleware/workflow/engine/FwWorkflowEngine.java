package com.reliefzk.middleware.workflow.engine;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.reliefzk.middleware.workflow.core.WorkflowContext;
import com.reliefzk.middleware.workflow.core.definations.FwWorkflow;
import com.googlecode.aviator.AviatorEvaluator;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * 工作流引擎
 * @author kui.zhouk
 */
public class FwWorkflowEngine extends FwWorkflowEngineContext {

    /**
     * 执行工作流
     *
     * @param workflowId
     * @param from
     * @param context
     * @return
     */
    public WorkflowResult start(String workflowId, String from, WorkflowContext context) {
        List<FwWorkflow.GateWay> gateWays = queryGateways(workflowId, from);
        if(CollectionUtils.isEmpty(gateWays)) {
            return WorkflowResult.newErrorResult(from, WorkFlowErrorEnum.Config_Err.name());
        }

        List<String> toList = execGateways(gateWays, context);
        if(toList.size() == 0) {
            return WorkflowResult.newErrorResult(from, WorkFlowErrorEnum.No_Result.name());
        }
        if(toList.size() != 1) {
            return WorkflowResult.newErrorResult(from, WorkFlowErrorEnum.To_Dupliate.name());
        }

        return WorkflowResult.newOkResult(from, toList.get(0));
    }

    private List<String> execGateways(List<FwWorkflow.GateWay> gateWays, WorkflowContext context) {
        List<String> toList = new ArrayList<>();
        for(FwWorkflow.GateWay gateWay : gateWays){
            boolean conditionResult = false;
            if(StringUtils.isNotEmpty(gateWay.getConditionExpr())){
                conditionResult = execExpression(gateWay.getConditionExpr(), context);
            }
            if(!conditionResult && gateWay.getSpringExprBean() != null){
                conditionResult = gateWay.getSpringExprBean().exprExec(context);
            }
            if(conditionResult) {
                toList.add(gateWay.getTo());
            }
        }
        return toList;
    }

    /**
     * 执行表达式
     * @param expr
     * @param context
     * @return
     */
    private boolean execExpression(String expr, Map<String, Object> context) {
        //expr = "${" + expr + "}";
        return (Boolean) AviatorEvaluator.execute(expr, context, true);
    }


}