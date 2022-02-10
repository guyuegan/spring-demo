package com.neo.bean.definition;

import com.neo.ioc.overview.entity.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class T4_BeanInstant {
    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/bean-instantiation-ctx.xml");
        System.out.println(beanFactory.getBean("userByStaticMethod", User.class));
        System.out.println(beanFactory.getBean("userByInstanceMethod", User.class));
        System.out.println(beanFactory.getBean("userByFactoryBean", User.class));
    }
}
