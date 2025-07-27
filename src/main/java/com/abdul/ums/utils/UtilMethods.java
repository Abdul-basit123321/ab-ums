package com.abdul.ums.utils;

import com.abdul.ums.entity.Role;
import com.abdul.ums.enums.RoleEnum;

public class UtilMethods {

    public static RoleEnum convertRoleToEnum(String roleName) {
        if (roleName.equalsIgnoreCase(RoleEnum.ROLE_USER.name()))
            return RoleEnum.ROLE_USER;

        return RoleEnum.ROLE_ADMIN;
    }
}
