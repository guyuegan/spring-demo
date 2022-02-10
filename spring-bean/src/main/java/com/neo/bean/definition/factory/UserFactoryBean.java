package com.neo.bean.definition.factory;

import com.neo.ioc.overview.entity.User;
import org.springframework.beans.factory.FactoryBean;

/**
 * 作为工厂的一种bean, 专门为生成其他bean服务
 */
public class UserFactoryBean implements FactoryBean<User> {
    @Override
    public User getObject() throws Exception {
        return new User("userByFactoryBean", 3);
    }

    @Override
    public Class<?> getObjectType() {
        return User.class;
    }
}
