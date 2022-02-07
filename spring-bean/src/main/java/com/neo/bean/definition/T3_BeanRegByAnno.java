package com.neo.bean.definition;

import com.neo.ioc.overview.entity.User;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

// 3. 通过 @Import 来进行导入
@Import(T3_BeanRegByAnno.Conf.class)
public class T3_BeanRegByAnno {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();

        // {com.neo.bean.definition.T3_BeanRegByAnno$Conf=com.neo.bean.definition.T3_BeanRegByAnno$Conf@4524411f}
         ctx.register(T3_BeanRegByAnno.class);

        // {t3_BeanRegByAnno.Conf=com.neo.bean.definition.T3_BeanRegByAnno$Conf@43a0cee9}
        // ctx.register(T3_BeanRegByAnno.Conf.class);

        ctx.refresh();
        //regBean(ctx, "myRegUser");

        /*
           虽然regBean(ctx, "userAlias")能够注册成功, 但是最终getBeansOfType获取不到，
           因为"userAlias"已经是springAnnoUser的别名, debug到DefaultListableBeanFactory.doGetBeanNamesForType可知
         */
        //regBean(ctx, "userAlias");

        /*
            注册bean, 如果出现同名，api注册的bean会覆盖spring通过扫描注解获取的bean
         */
        regBean(ctx, "springAnnoUser");

        // spring生成名称 => com.neo.ioc.overview.entity.User#0
        regBean(ctx, null);


        System.out.println(ctx.getBeansOfType(Conf.class));

        System.out.println(ctx.getBeansOfType(User.class));
        ctx.close();
    }

    private static void regBean(BeanDefinitionRegistry registry, String beanName) {
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        BeanDefinition definition =
                builder.addPropertyValue("name", "myRegUser")
                        .addPropertyValue("age", 22)
                        .getBeanDefinition();

        if (!StringUtils.hasText(beanName)) {
            beanName = BeanDefinitionReaderUtils.generateBeanName(definition, registry);
        }

        registry.registerBeanDefinition(beanName, definition);
    }

    // 2. 通过 @Component 方式
    @Component // 定义当前类作为 Spring Bean（组件）
    public static class Conf {

        // 1. 通过 @Bean 方式定义
        @Bean(name = {"springAnnoUser", "userAlias"})
        private User user() {
            User user = new User();
            user.setName("springAnnoUser");
            user.setAge(11);
            return user;
        }
    }
}
