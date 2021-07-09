package com.zbing;

import com.alibaba.fastjson.JSON;
import com.zbing.excel.Bean;
import com.zbing.reflect.ReflectUtil;
import org.junit.Test;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName ReflectTest
 * @Author zbing
 * @Description
 * @Date 2021/7/8 17:58
 **/
public class ReflectTest {
    @Test
    public void DynamicBean() throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        Bean bean = new Bean();
//        user.setN1("Daisy");
        System.out.println("==：" + JSON.toJSONString(bean));
        Map<String, Object> propertiesMap = new HashMap<String, Object>();
        propertiesMap.put("age", 18);
        propertiesMap.put("n1", 20);
        propertiesMap.put("n2", "66");
        Object obj = ReflectUtil.getObject(bean, propertiesMap);
        final Class<?> aClass = obj.getClass();
        PropertyDescriptor pd = new PropertyDescriptor("n2", aClass);
        final Method readMethod = pd.getReadMethod();
        System.out.println(readMethod.invoke(obj));
        System.out.println("动态添加之后：" + JSON.toJSONString(obj));
    }

    @Test
    public void test() throws Exception {
        Bean bean = new Bean();
        bean.setAa("123123");
        /*PropertyDescriptor propertyDescriptor = new PropertyDescriptor("aa", Bean.class);
        propertyDescriptor.getWriteMethod().invoke(bean, "111");
        final Object aa = propertyDescriptor.getReadMethod().invoke(bean);
        System.out.println(aa);
*/
        final BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass());
        final PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor p : propertyDescriptors) {
            System.out.println(p.getReadMethod().invoke(bean));
        }

    }
}
