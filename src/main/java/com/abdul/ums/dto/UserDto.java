package com.abdul.ums.dto;

import com.abdul.ums.entity.Role;
import com.abdul.ums.entity.User;
import com.abdul.ums.enums.RoleEnum;
import com.abdul.ums.utils.UtilMethods;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class UserDto {

    private String name;
    private String email;
    private List<RoleEnum> roleEnum;

    public static UserDto fromUser(User user) {
        UserDto dto = new UserDto();
        dto.setEmail(user.getEmail());
        dto.setName(user.getName());
        dto.setRoleEnum(user.getRoles().stream().map(x-> UtilMethods.convertRoleToEnum(x.getName())).collect(Collectors.toList()));
        return dto;
    }


}
