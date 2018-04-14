/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.reliefzk.middleware.workflow.aviator;

import java.util.Map;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.collections.Maps;

/**
 *
 * @author kui.zhouk
 * @version $Id: AviatorTest.java, v 0.1 2018年01月26日 20:03 kui.zhouk Exp $
 */
public class AviatorTest {


    @Test
    public void testCompile() {
        Expression expression = AviatorEvaluator.compile("'key1 == 'key2'");
        System.err.println(expression);
    }

    @Test(dataProvider = "testExecData")
    public void testExec(String key1, String key2, String key3, String key4) {
        Map context = Maps.newHashMap();
        context.put("key1", key1);
        context.put("key2", key2);
        context.put("key3", key3);
        context.put("key4", key4);
        boolean result = (Boolean) AviatorEvaluator.execute(" (key1 == 'check') && (key3 == key4) ", context);
        Assert.assertTrue(result);
    }

    @DataProvider
    private Object[][] testExecData(){
        return new Object[][]{
                {"check", "check", "issue", "issue"},
                //{"check", "check", "issue", "NoIssue"}
        };
    }
}