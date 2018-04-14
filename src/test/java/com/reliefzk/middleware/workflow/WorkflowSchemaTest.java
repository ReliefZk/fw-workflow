package com.reliefzk.middleware.workflow;

import java.util.Map;

import com.alibaba.fastjson.JSON;

import com.reliefzk.middleware.workflow.core.WorkflowContext;
import com.reliefzk.middleware.workflow.core.definations.FwWorkflow;
import com.reliefzk.middleware.workflow.engine.FwWorkflowEngine;
import com.reliefzk.middleware.workflow.engine.WorkflowResult;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.Test;

/**
 * Unit test for simple App.
 */
public class WorkflowSchemaTest {

    private FwWorkflowEngine fwWorkflowEngine;

    @Test
    public void testEngine(){
        WorkflowContext context = new WorkflowContext();
        context.put("", "");
        WorkflowResult result = fwWorkflowEngine.start("testWorkflow", "start", context);
        if(result.getSuccess()){
            System.err.println(result.getTo());
        }
    }


    @Test
    public void testApp() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("/spring/workflow/spring-context*.xml");
        Map<String, FwWorkflow> map = ctx.getBeansOfType(FwWorkflow.class);

        System.err.println(JSON.toJSONString(map.values()));
    }
}
