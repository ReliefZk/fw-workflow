package com.reliefzk.middleware.workflow.core.definations;

import java.util.List;

import com.reliefzk.middleware.workflow.core.definations.support.WorkflowExprBean;
import com.reliefzk.middleware.workflow.core.definations.support.WorkflowListener;

/**
 *
 * @author kui.zhouk
 */
public class FwWorkflow {

    private String id;

    private List<GateWay> gateWays;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "FwWorkflow{" +
                "id='" + id + '\'' +
                ", gateWays=" + gateWays +
                '}';
    }

    public List<GateWay> getGateWays() {
        return gateWays;
    }

    public void setGateWays(List<GateWay> gateWays) {
        this.gateWays = gateWays;
    }

    public static class GateWay {
        private String           from;
        private String           to;
        private String           conditionExpr;
        private WorkflowExprBean springExprBean;
        private WorkflowListener preListener;
        private WorkflowListener postListener;

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

        public String getConditionExpr() {
            return conditionExpr;
        }

        public void setConditionExpr(String conditionExpr) {
            this.conditionExpr = conditionExpr;
        }

        public WorkflowExprBean getSpringExprBean() {
            return springExprBean;
        }

        public void setSpringExprBean(WorkflowExprBean springExprBean) {
            this.springExprBean = springExprBean;
        }

        public WorkflowListener getPreListener() {
            return preListener;
        }

        public void setPreListener(WorkflowListener preListener) {
            this.preListener = preListener;
        }

        public WorkflowListener getPostListener() {
            return postListener;
        }

        public void setPostListener(WorkflowListener postListener) {
            this.postListener = postListener;
        }

        @Override
        public String toString() {
            return "gateWay{" +
                    "from='" + from + '\'' +
                    ", to='" + to + '\'' +
                    ", conditionExpr='" + conditionExpr + '\'' +
                    ", springExprBean='" + springExprBean + '\'' +
                    ", preListener='" + preListener + '\'' +
                    ", postListener='" + postListener + '\'' +
                    '}';
        }
    }
}