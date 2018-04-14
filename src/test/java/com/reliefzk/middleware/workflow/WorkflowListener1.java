/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.reliefzk.middleware.workflow;

import com.reliefzk.middleware.workflow.core.WorkflowContext;
import com.reliefzk.middleware.workflow.core.definations.support.WorkflowListener;

/**
 *
 * @author kui.zhouk
 * @version $Id: WorkflowListener1.java, v 0.1 2018年01月26日 18:28 kui.zhouk Exp $
 */
public class WorkflowListener1 implements WorkflowListener {
    @Override
    public void invoke(WorkflowContext context) {
        System.err.println("WorkflowListener1");
    }
}