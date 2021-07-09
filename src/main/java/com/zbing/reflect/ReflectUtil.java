package com.zbing.reflect;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zbing
 * 利用cglib 动态增加类属性+
 *
 */
public class ReflectUtil {
    public final static String CLASS_DATE = "class java.util.Date";

    /**
     * 为obj增加属性addProperties
     *
     * @param obj 操作对象
     * @param addProperties 需要增加的属性
     * @return
     */
    public static Object getObject(Object obj, Map<String, Object> addProperties) {
        PropertyDescriptor[] descriptors = new PropertyDescriptor[0];
        try {
            descriptors = Introspector.getBeanInfo(obj.getClass()).getPropertyDescriptors();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        //存放原有字段名字和字段属性
        Map<String, Class> propertyMap = new HashMap<>();
        //存放原有字段名字和值
        Map<String, Object> valMap = new HashMap<>();
        //原有字段
        for (PropertyDescriptor d : descriptors) {
            if (!"class".equalsIgnoreCase(d.getName())) {
                propertyMap.put(d.getName(), d.getPropertyType());
                try {
                    valMap.put(d.getName(),d.getReadMethod().invoke(obj));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        //新增字段
        addProperties.forEach((k, v) -> {
            String classString = v.getClass().toString();
            //对日期进行处理
            if (classString.equals(CLASS_DATE)) {
                propertyMap.put(k, Long.class);
            } else {
                propertyMap.put(k, v.getClass());
            }

        });
        DynamicBean dynamicBean = new DynamicBean(obj.getClass(), propertyMap);
        //原有字段赋值
        propertyMap.forEach((k, v) -> {
            try {
                if (!addProperties.containsKey(k)) {
                    dynamicBean.setValue(k, valMap.get(k));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        //新增字段赋值
        addProperties.forEach((k, v) -> {
            try {
                String classString = v.getClass().toString();
                //动态添加的字段为date类型需要进行处理
                if (classString.equals(CLASS_DATE)) {
                    Date date = (Date) v;
                    dynamicBean.setValue(k, date.getTime());
                } else {
                    dynamicBean.setValue(k, v);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return dynamicBean.getTarget();
    }
}

