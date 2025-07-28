package com.abdul.ums.dto;

import lombok.Data;

@Data
public class UserRequest {

    private String name;
    private String email;
    private String password;

    public UserRequest(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
