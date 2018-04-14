package com.reliefzk.middleware.workflow.core.definations.support;

import com.reliefzk.middleware.workflow.core.WorkflowContext;

/**
 * 工作流表达式同等接口
 *
 * @author kui.zhouk
 */
public interface WorkflowExprBean {

    Boolean exprExec(WorkflowContext context);

}