package com.abdul.ums.utils;

import com.abdul.ums.entity.Role;
import com.abdul.ums.enums.RoleEnum;

public class UtilMethods {

    public static Role getUserRole(){
        Role userRole = new Role();
        userRole.setName(RoleEnum.ROLE_USER.name());
        return userRole;
    }
}
