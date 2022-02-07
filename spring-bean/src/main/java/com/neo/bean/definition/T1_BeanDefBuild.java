package com.neo.bean.definition;

import com.neo.ioc.overview.entity.User;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.GenericBeanDefinition;

public class T1_BeanDefBuild {
    public static void main(String[] args) {
        // 1.通过 BeanDefinitionBuilder 构建 (底层也是GenericBeanDefinition)
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        // 设置属性
        builder.addPropertyValue("name", "userDef")
               .addPropertyValue("age", 20);
        // 获取 BeanDefinition 实例
        BeanDefinition beanDefinition = builder.getBeanDefinition();
        // BeanDefinition 并非 Bean 终态，可以继续修改
        System.out.println(beanDefinition);

        // 2.通过 AbstractBeanDefinition 以及派生类
        GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
        // 设置 Bean 类型
        genericBeanDefinition.setBeanClass(User.class);
        // 底层：List<PropertyValue>，kv对集合
        MutablePropertyValues propertyValues = new MutablePropertyValues();
        propertyValues.add("name", "userDef2")
                      .add("age", 22);
        // BeanDefinition没有，子类才有的操作
        genericBeanDefinition.setPropertyValues(propertyValues);
        System.out.println(genericBeanDefinition);
        System.out.println(genericBeanDefinition.getOriginatingBeanDefinition());
    }

}
