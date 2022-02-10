package com.neo.bean.definition;

import com.neo.bean.definition.factory.DefaultUserFactory;
import com.neo.bean.definition.factory.UserFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Iterator;
import java.util.ServiceLoader;
import java.util.stream.Stream;

public class T5_SpecialBeanInstant {
    public static void main(String[] args) {
        // 通过ServiceLoader创建bean
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/special-bean-instantiation-ctx.xml");
        ServiceLoader<UserFactory> userFactoryServiceLoader = beanFactory.getBean("userFactoryServiceLoader", ServiceLoader.class);
        userFactoryServiceLoader.forEach(userFactory -> System.out.println(userFactory.createUser()));
        serviceLoaderDemo();

        // 通过AutowireCapableBeanFactory创建bean
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:/META-INF/special-bean-instantiation-ctx.xml");
        // 通过 ApplicationContext 获取 AutowireCapableBeanFactory
        AutowireCapableBeanFactory autowireCapableBeanFactory = context.getAutowireCapableBeanFactory();
        // 注意：createBean 而不是 getBean
        UserFactory userFactory = autowireCapableBeanFactory.createBean(DefaultUserFactory.class);
        System.out.println(userFactory.createUser());
    }

    /**
     * 需要在/META-INF/services/目录下创建服务清单（接口全名作为文件名）{@link ServiceLoader}
     */
    private static void serviceLoaderDemo() {
        // ServiceLoader相当于是UserFactory容器
        ServiceLoader<UserFactory> loader = ServiceLoader.load(UserFactory.class, Thread.currentThread().getContextClassLoader());
        for (UserFactory userFactory : loader) {
            System.out.println(userFactory.createUser());
        }
    }


}
