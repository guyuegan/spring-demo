package com.neo.ioc.overview.repo;

import com.neo.ioc.overview.entity.User;
import lombok.Data;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ApplicationContext;

import java.util.Collection;

@Data
public class UserRepo {
    private Collection<User> users; // 自定义 Bean

    private BeanFactory beanFactory; // spring内建 非bean 对象（依赖）

    private ObjectFactory<User> userObjectFactory;

    private ObjectFactory<ApplicationContext> contextObjectFactory;
}

