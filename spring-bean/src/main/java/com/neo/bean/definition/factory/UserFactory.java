package com.neo.bean.definition.factory;

import com.neo.ioc.overview.entity.User;

public interface UserFactory {
    default User createUser() {
        return new User("userByInstanceMethod", 2);
    }
}
