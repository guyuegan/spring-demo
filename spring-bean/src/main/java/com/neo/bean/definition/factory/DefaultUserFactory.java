package com.neo.bean.definition.factory;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class DefaultUserFactory implements UserFactory, InitializingBean, DisposableBean {
    @PostConstruct
    public void init() {
        System.out.println(" @PostConstruct初始化  ");
    }

    public void customInitMethod() {
        System.out.println(" customInitMethod初始化   ");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println(" InitializingBean初始化   ");
    }


    @PreDestroy
    public void preDestroy() {
        System.out.println("    @PreDestroy销毁  ");
    }

    public void customDestroyMethod() {
        System.out.println(" customDestroyMethod销毁  ");
    }

    @Override
    public void destroy() {
        System.out.println("    DisposableBean销毁  ");
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("DefaultUserFactory对象正在被垃圾回收");
    }
}
