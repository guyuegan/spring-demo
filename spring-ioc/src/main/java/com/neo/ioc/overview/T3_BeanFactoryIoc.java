package com.neo.ioc.overview;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

import java.util.Arrays;

public class T3_BeanFactoryIoc {
    public static void main(String[] args) {
        // 创建BeanFactory：没有ApplicationContext也可以依赖查找，但是没有env, 国际化等高级特性
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 加载xml配置：XmlBeanDefinitionReader
        XmlBeanDefinitionReader definitionReader = new XmlBeanDefinitionReader(beanFactory);
        int beanCnt = definitionReader.loadBeanDefinitions("classpath:/META-INF/dep-inject-ctx.xml");
        System.out.println(beanCnt);

        System.out.println(Arrays.toString(beanFactory.getBeanDefinitionNames()));
    }

}
