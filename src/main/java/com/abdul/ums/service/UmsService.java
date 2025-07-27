package com.abdul.ums.service;

import com.abdul.ums.dto.UserDto;
import com.abdul.ums.dto.UserRequest;

import java.util.List;

public interface UmsService {

    List<UserDto> getAllUsers();
    UserDto addUser(UserRequest userRequest);
    UserDto deleteUser(String email);
    UserDto updateUser(String email,String password,String name);
    UserDto updateRole(String email,List<String> roleName);
}
