package com.neo.bean.definition;

import com.neo.bean.definition.factory.DefaultUserFactory;
import com.neo.bean.definition.factory.UserFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

/**
 * 初始化和销毁都是：先Java方式，再Spring方式，后自定义方式
 */
@Configuration // Configuration Class
public class T6_BeanInit {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(T6_BeanInit.class);
        ctx.refresh();
        //  @Lazy表示在上下文启动后，按需初始化（用到才初始化，eg: 如果没有下面的ctx.getBean(UserFactory.class)，则不会出生化）
        // org.springframework.context.support.AbstractApplicationContext.finishBeanFactoryInitialization
        System.out.println("===应用上下文已经启动===");
        System.out.println(ctx.getBean(UserFactory.class));
        System.out.println("===应用上下文准备关闭===");
        ctx.close();
        System.out.println("===应用上下文已经关闭===");
    }

    // initMethod处理：AbstractBeanDefinition.setInitMethodName
    // DisposableBean处理：AbstractApplicationContext.close ==》DefaultSingletonBeanRegistry.destroyBean
    // @PreDestroy/@PostConstruct处理：InitDestroyAnnotationBeanPostProcessor.buildLifecycleMetadata
    @Bean(initMethod = "customInitMethod", destroyMethod = "customDestroyMethod")
    //@Lazy
    public UserFactory userFactory() {
        return new DefaultUserFactory();
    }
}


