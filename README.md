# fw-workflow (flyweight-workflow)
蝇量级工作流引擎，不准守bpmn2.0规范

配置：
```
    <fw-workflow:workflow id="testWorkflow">
            <fw-workflow:gateway from="start" to="check" conditionExpr="${1=1}" postListener="listener1"/>
            <fw-workflow:gateway from="check" to="confirm" springExprBean="checkExpr" preListener="listener2"/>
    </fw-workflow:workflow>
    
    <bean id="checkExpr" class="com.reliefzk.middleware.workflow.WorkflowExprBean1"/>
    <bean id="listener1" class="com.reliefzk.middleware.workflow.WorkflowListener1"/>
    <bean id="listener2" class="com.reliefzk.middleware.workflow.WorkflowListener1"/>
```
调用：
```
WorkflowContext context = new WorkflowContext();
context.put("", "");
WorkflowResult result = fwWorkflowEngine.start("testWorkflow", "start", context);
if(result.getSuccess()){
    String nextStep = result.getTo();
}
```