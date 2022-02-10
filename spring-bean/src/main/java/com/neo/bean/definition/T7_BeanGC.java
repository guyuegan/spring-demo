package com.neo.bean.definition;

import com.neo.bean.definition.factory.DefaultUserFactory;
import com.neo.bean.definition.factory.UserFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;

/**
 * todo 什么是GC
 * https://blog.csdn.net/troubleshooter/article/details/78351757
 */
public class T7_BeanGC {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        // 使用T6_BeanInit作为配置类
        ctx.register(T6_BeanInit.class);
        ctx.refresh();
        System.out.println("===应用上下文已经启动===");
        System.out.println("===应用上下文准备关闭===");
        ctx.close();
        // "DefaultUserFactory对象正在被垃圾回收"， 可能在 "应用上下文已经关闭" 之前，也可能之后，随机
        System.gc();
        System.out.println("===应用上下文已经关闭===");
    }

}


