package com.abdul.ums.controller;

import com.abdul.ums.dto.UserDto;
import com.abdul.ums.model.APIResponse;
import com.abdul.ums.service.UmsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/ums")
@Slf4j
@RequiredArgsConstructor
public class UmsController {

    private final UmsService umsService;

    //Admins only tasks
     // -- create user
     // -- Read user
     // -- Update user
     // -- Delete user
     // -- Assign roles to users

    @GetMapping
    public APIResponse<List<UserDto>> getAllUsers() {
        log.info("GetAllUsers called");
        return APIResponse.ok(umsService.getAllUsers());
    }

    //User only tasks
     // -- view own profile
     // -- update own profile




}
