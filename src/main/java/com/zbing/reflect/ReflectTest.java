package com.zbing.reflect;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName ReflectTest
 * @Author zbing
 * @Description
 * @Date 2021/7/9 10:42
 **/
public class ReflectTest {
    public static void main(String[] args) {
        BeanTest beanTest = new BeanTest();
        beanTest.setT1("ttt1");
        System.out.println("initial Object : " + JSON.toJSONString(beanTest));
        Map<String, Object> addProperties = new HashMap<>();
        addProperties.put("t2", "ttt2");
        final Object object = ReflectUtil.getObject(beanTest, addProperties);
        System.out.println("after Object : " + JSON.toJSONString(object));
    }
}

class BeanTest {
    private String t1;

    public String getT1() {
        return t1;
    }

    public void setT1(String t1) {
        this.t1 = t1;
    }
}