/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.reliefzk.middleware.workflow;

import com.reliefzk.middleware.workflow.core.WorkflowContext;
import com.reliefzk.middleware.workflow.core.definations.support.WorkflowExprBean;

/**
 *
 * @author kui.zhouk
 */
public class WorkflowExprBean1 implements WorkflowExprBean {

    @Override
    public Boolean exprExec(WorkflowContext context) {
        return true;
    }
}