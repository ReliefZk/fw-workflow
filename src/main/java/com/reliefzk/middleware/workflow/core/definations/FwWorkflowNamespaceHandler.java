package com.reliefzk.middleware.workflow.core.definations;

import java.util.HashSet;
import java.util.Set;

import com.reliefzk.middleware.workflow.core.definations.FwWorkflow.GateWay;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.ManagedList;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;
import org.springframework.dao.DuplicateKeyException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author kui.zhouk
 */
public class FwWorkflowNamespaceHandler extends NamespaceHandlerSupport {

    @Override
    public void init() {
        registerBeanDefinitionParser("workflow", new AmlBeanDefinitionParser());
    }

    public static class AmlBeanDefinitionParser  extends AbstractSingleBeanDefinitionParser {

        /* workflow 名称 列表 */
        private Set<String> workflowSet = new HashSet<>();

        protected Class getBeanClass(Element element) {
            return FwWorkflow.class;
        }

        protected void doParse(Element element, BeanDefinitionBuilder bean) {
            String workflowName = element.getAttribute("id");
            if(workflowSet.contains(workflowName)) {
                throw new DuplicateKeyException( workflowName + " duplicate!");
            }
            workflowSet.add(workflowName);
            bean.addPropertyValue("id", workflowName);
            MutablePropertyValues propertyValues = bean.getBeanDefinition().getPropertyValues();

            ManagedList gatewayList = new ManagedList();

            NodeList nodeList = element.getChildNodes();
            for(int index = 0; index < nodeList.getLength(); index++){
                Node childNode = nodeList.item(index);
                String nodeName = childNode.getLocalName();
                if(StringUtils.equals(nodeName, "gateway")){
                    parseGateWay((Element) childNode, gatewayList);
                }
            }

            if (CollectionUtils.isNotEmpty(gatewayList)) {
                propertyValues.addPropertyValue("gateWays", gatewayList);
            }
        }

        /**
         * 解析gateway节点
         * @param gatewayNode
         * @param gatewayList
         */
        private void parseGateWay(Element gatewayNode, ManagedList gatewayList) {
            boolean hasExpr = false;

            String from = gatewayNode.getAttribute("from");
            String to = gatewayNode.getAttribute("to");
            String conditionExpr = gatewayNode.getAttribute("conditionExpr");
            if(StringUtils.isNotEmpty(conditionExpr)) {
                hasExpr = true;
            }
            String springExprBean = gatewayNode.getAttribute("springExprBean");
            if(hasExpr && StringUtils.isNotEmpty(springExprBean)) {
                throw new RuntimeException("Duplicate Expresion!");
            }
            /* 前置监听器 */
            String preListener = gatewayNode.getAttribute("preListener");
            /* 后置监听器 */
            String postListener = gatewayNode.getAttribute("postListener");

            BeanDefinitionBuilder listenerBdb = BeanDefinitionBuilder.genericBeanDefinition();
            listenerBdb.getRawBeanDefinition().setBeanClass(GateWay.class);
            MutablePropertyValues propertyValues = listenerBdb.getBeanDefinition().getPropertyValues();

            propertyValues.addPropertyValue("from", from);
            propertyValues.addPropertyValue("to", to);
            propertyValues.addPropertyValue("conditionExpr", conditionExpr);


            addPropertyValue(propertyValues,"springExprBean", springExprBean);
            addPropertyValue(propertyValues,"preListener", preListener);
            addPropertyValue(propertyValues,"postListener", postListener);

            BeanDefinitionHolder holder = new BeanDefinitionHolder(listenerBdb.getBeanDefinition(), from + "To" + to);
            gatewayList.add(holder);
        }


        private void addPropertyValue(MutablePropertyValues propertyValues, String name, String beanName){
            if(StringUtils.isNotEmpty(beanName)){
                propertyValues.addPropertyValue(name, new RuntimeBeanReference(beanName));
            }
        }
    }

}