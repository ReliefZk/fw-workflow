package com.reliefzk.middleware.workflow.engine;

/**
 * 工作流错误
 * @author kui.zhouk
 */
public enum WorkFlowErrorEnum {
    /* 工作流不存在 */
    Not_Exists,
    /* 配置错误 */
    Config_Err,
    /* 多流出 */
    To_Dupliate,
    /* 没结果 */
    No_Result;
}