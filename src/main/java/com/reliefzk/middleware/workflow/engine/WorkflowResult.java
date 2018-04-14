package com.reliefzk.middleware.workflow.engine;

/**
 *
 * @author kui.zhouk
 */
public class WorkflowResult {
    /* 工作执行是否成功 */
    private Boolean success;
    private String from;
    private String to;
    private String errMsg;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    @Override
    public String toString() {
        return "WorkflowResult{" +
                "success=" + success +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", errMsg='" + errMsg + '\'' +
                '}';
    }

    public static WorkflowResult newErrorResult(String from, String errMsg){
        WorkflowResult result = new WorkflowResult();
        result.setSuccess(false);
        result.setFrom(from);
        result.setErrMsg(errMsg);
        return result;
    }

    public static WorkflowResult newOkResult(String from, String to) {
        WorkflowResult result = new WorkflowResult();
        result.setSuccess(true);
        result.setFrom(from);
        result.setTo(to);
        return result;
    }
}