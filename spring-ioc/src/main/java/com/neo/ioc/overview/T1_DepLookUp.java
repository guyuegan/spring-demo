package com.neo.ioc.overview;

import com.neo.ioc.overview.entity.SupUser;
import com.neo.ioc.overview.entity.User;
import com.neo.ioc.overview.anno.Vip;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

public class T1_DepLookUp {
    public static void main(String[] args) {
        // ApplicationContext本质：BeanFactory
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/dep-lookup-ctx.xml");

        // 1.根据名称（bean唯一标识）查找
        lookupInRealTime(beanFactory);
        // 延迟查找
        lookupInLazyMode(beanFactory);

        // 2.根据类型查找
        lookupByType(beanFactory);
        lookupCollectionByType(beanFactory);

        // 3.根据名称+类型
        lookupByNameAndType(beanFactory);

        // 4.根据注解查找
        lookupByAnno(beanFactory);

    }

    private static void lookupByAnno(BeanFactory beanFactory) {
        // 还是ListableBeanFactory比较强，可以根据注解获取
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            // 强转，骗一下编译器
            Map<String, User> vips = (Map) listableBeanFactory.getBeansWithAnnotation(Vip.class);
            System.out.println("根据注解实时查找到所有user：" + vips);
        }
    }

    private static void lookupByNameAndType(BeanFactory beanFactory) {
        User vip1 = beanFactory.getBean("VIP1", SupUser.class);
        User u1 = beanFactory.getBean("u1", User.class);
        System.out.println("根据类型和名称查找到u1：" + u1 +"; vip1: " + vip1);

        User vip11 = beanFactory.getBean("VIP1", User.class);
        System.out.println("根据父类类型和实例名称查找到vip1：" + vip11);
        // Bean named 'u1' is expected to be of type 'com.neo.ioc.overview.VipUser'
        // but was actually of type 'com.neo.ioc.overview.entity.User'
        // User u11 = beanFactory.getBean("u1", VipUser.class);
        // System.out.println("根据子类类型和实例名称查找到u1：" + u11);
    }


    private static void lookupCollectionByType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            // map: id => instance
            Map<String, User> userMap = ((ListableBeanFactory) beanFactory).getBeansOfType(User.class);
            System.out.println("根据类型实时查找到所有user：" + userMap);
        }
    }

    // Q: expected single matching bean but found 2: u1,VIP1
    // A: 需要标注primary
    private static void lookupByType(BeanFactory beanFactory) {
        // 通过类型查找支持泛型，说明是3.0以后加入的，Spring3.0开始全面支持Java5
        User u1 = beanFactory.getBean(User.class);
        System.out.println("根据类型实时查找：" + u1);
    }

    private static void lookupInRealTime(BeanFactory beanFactory) {
        User u1 = (User) beanFactory.getBean("u1");
        System.out.println("根据名称实时查找：" + u1);
    }

    private static void lookupInLazyMode(BeanFactory beanFactory) {
        ObjectFactory<User> objectFactory = (ObjectFactory<User>) beanFactory.getBean("objectFactory");
        System.out.println("根据名称延迟查找：" + objectFactory.getObject());
    }
}
