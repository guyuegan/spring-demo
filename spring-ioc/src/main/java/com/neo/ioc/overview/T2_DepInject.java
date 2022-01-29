package com.neo.ioc.overview;

import com.neo.ioc.overview.anno.Vip;
import com.neo.ioc.overview.entity.SupUser;
import com.neo.ioc.overview.entity.User;
import com.neo.ioc.overview.repo.UserRepo;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractRefreshableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;

import java.util.Map;

public class T2_DepInject {
    public static void main(String[] args) {
        // ApplicationContext本质：BeanFactory
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/dep-inject-ctx.xml");

        UserRepo userRepo = beanFactory.getBean("userRepo", UserRepo.class);
        System.out.println(userRepo.getUsers());
        System.out.println(beanFactory);

        /**
         * 通过下面的示例，依赖注入能找到的依赖，通过依赖查找不一定找到
         */

        // 【依赖来源一：自定义bean】依赖注入：DefaultListableBeanFactory
        System.out.println(userRepo.getBeanFactory());
        // 通过注入获取的beanFactory和通过配置文件创建的beanFactory是否一样
        whoIsIocContainer(beanFactory, userRepo);

        // 依赖查找（报错）：NoSuchBeanDefinitionException
        // System.out.println(beanFactory.getBean(BeanFactory.class));

        // 【依赖来源二：内建依赖】延迟
        System.out.println(userRepo.getUserObjectFactory().getObject());
        ApplicationContext context = userRepo.getContextObjectFactory().getObject();
        System.out.println(context);
        System.out.println(beanFactory == context);

        // 【依赖来源三：容器内建bean】
        Environment environment = beanFactory.getBean(Environment.class);
        System.out.println(environment);
    }

    private static void whoIsIocContainer(BeanFactory beanFactory, UserRepo userRepo) {
        /** false的原因：
         * beanFactory是ClassPathXmlApplicationContext,
         * userRepo.getBeanFactory()是DefaultListableBeanFactory
         *
         * 源码分析：
         * ClassPathXmlApplicationContext《--。。ConfigurableApplicationContext《--。。ApplicationContext《-。。BeanFactory
         *
         * 通过源码可以发现ApplicationContext是BeanFactory子类，
         * 相比BeanFactory增强了功能: AOP, I18N, Event, WebApplicationContext
         * ConfigurableApplicationContext#getBeanFactory（在AbstractRefreshableApplicationContext实现）,
         * 其实是通过组合DefaultListableBeanFactory来完成（类型代理，本身继承BeanFactory, 但实际是内部套了一个真正的BeanFactory）
         *
         */

        System.out.println("== beanFactory same as context? " + (userRepo.getBeanFactory() == beanFactory));

        AbstractRefreshableApplicationContext context = (AbstractRefreshableApplicationContext) beanFactory;
        System.out.println("== beanFactory same as context.getBeanFactory()? " + (userRepo.getBeanFactory() == context.getBeanFactory()));
    }


}
