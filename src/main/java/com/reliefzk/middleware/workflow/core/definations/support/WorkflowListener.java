package com.reliefzk.middleware.workflow.core.definations.support;

import com.reliefzk.middleware.workflow.core.WorkflowContext;

/**
 *
 * @author kui.zhouk
 */
public interface WorkflowListener {

    void invoke(WorkflowContext context);

}