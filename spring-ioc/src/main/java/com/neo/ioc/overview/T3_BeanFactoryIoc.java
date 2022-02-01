package com.neo.ioc.overview;

import com.neo.ioc.overview.entity.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

import java.util.Arrays;
import java.util.Map;

public class T3_BeanFactoryIoc {
    public static void main(String[] args) {
        // 创建BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 加载配置：XmlBeanDefinitionReader
        XmlBeanDefinitionReader definitionReader = new XmlBeanDefinitionReader(beanFactory);
        int beanCnt = definitionReader.loadBeanDefinitions("classpath:/META-INF/dep-inject-ctx.xml");
        System.out.println(beanCnt);

        System.out.println(Arrays.toString(beanFactory.getBeanDefinitionNames()));
    }

}
