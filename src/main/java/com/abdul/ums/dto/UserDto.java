package com.abdul.ums.dto;

import com.abdul.ums.entity.User;
import lombok.Data;

@Data
public class UserDto {

    private String name;
    private String email;

    public static UserDto fromUser(User user) {
        UserDto dto = new UserDto();
        dto.setEmail(user.getEmail());
        dto.setName(user.getName());
        return dto;
    }
}
