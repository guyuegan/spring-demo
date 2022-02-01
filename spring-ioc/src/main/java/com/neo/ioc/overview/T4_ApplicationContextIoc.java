package com.neo.ioc.overview;

import com.neo.ioc.overview.entity.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.Map;

/**
 * 带注解能力的ApplicationContext作为Ioc容器
 */
// 没有@Configuration, 只有@Bean也可以纳入ioc
@Configuration
public class T4_ApplicationContextIoc {
    public static void main(String[] args) {
        // 创建BeanFactory
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 将当前类作为配置类
        applicationContext.register(T4_ApplicationContextIoc.class);
        // AnnotationConfigApplicationContext@5010be6 has not been refreshed yet
        applicationContext.refresh();

        System.out.println(Arrays.toString(applicationContext.getBeanNamesForType(User.class)));

        lookupCollectionByType(applicationContext);
    }

    /**
     * Configuration problem: @Bean method 'annoUser' must not be private or final; change the method's modifiers to continue
     * todo 为什么没有@Configuration可以private，并且成功纳入ioc
     */
    @Bean
    public User annoUser() {
        User user = new User();
        user.setName("anno");
        user.setAge(1);
        return user;
    }

    private static void lookupCollectionByType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            // map: id => instance
            Map<String, User> userMap = ((ListableBeanFactory) beanFactory).getBeansOfType(User.class);
            System.out.println("根据类型查找到所有user：" + userMap);
        }
    }
}

