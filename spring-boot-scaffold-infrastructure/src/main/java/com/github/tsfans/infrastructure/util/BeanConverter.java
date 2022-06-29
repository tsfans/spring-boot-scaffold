/**
 * Copyright (C) 2020 ~ 2020 Meituan. All Rights Reserved.
 */
package com.github.tsfans.infrastructure.util;

import com.github.tsfans.domain.user.User;
import com.github.tsfans.infrastructure.db.po.UserPO;

/**
 * @author:   		HuLin
 * @date:    		2020年10月9日 下午9:18:34
 * @description:    DESCRIPTION
 * @version:   		v1.0
 */
public class BeanConverter {

    private BeanConverter() {}
    
    public static UserPO convertToUserPO(User user) {
        UserPO userPO = new UserPO();
        userPO.setId(user.getId());
        userPO.setUsername(user.getUsername());
        userPO.setPassword(user.getPassword());
        userPO.setVersion(user.getVersion());
        return userPO;
    }
    
    public static User convertToUser(UserPO userPO) {
        User user = new User(userPO.getId(), userPO.getUsername());
        user.setupPassword(userPO.getPassword());
        user.setVersion(userPO.getVersion());
        return user;
    }
    
}
