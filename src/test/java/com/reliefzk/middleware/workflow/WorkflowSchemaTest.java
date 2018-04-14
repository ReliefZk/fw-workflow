package com.reliefzk.middleware.workflow;

import java.util.Map;

import com.alibaba.fastjson.JSON;

import com.reliefzk.middleware.workflow.core.definations.FwWorkflow;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Unit test for simple App.
 */
public class WorkflowSchemaTest {


    @Test
    public void testApp() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("/spring/workflow/spring-context*.xml");
        Map<String, FwWorkflow> map = ctx.getBeansOfType(FwWorkflow.class);
        System.err.println(JSON.toJSONString(map.values()));
    }
}
