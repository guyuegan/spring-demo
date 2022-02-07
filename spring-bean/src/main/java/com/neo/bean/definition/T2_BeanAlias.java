package com.neo.bean.definition;

import com.neo.ioc.overview.entity.User;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class T2_BeanAlias {
    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/bean-def-ctx.xml");
        System.out.println(beanFactory.getBean("VIP1") == beanFactory.getBean("very important person"));
    }

}
