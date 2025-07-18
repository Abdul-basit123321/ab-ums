package com.abdul.ums.controller;

import com.abdul.ums.model.APIResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/ums")
@Slf4j
public class UmsController {

    //Admins only tasks
     // -- create user
     // -- Read user
     // -- Update user
     // -- Delete user
     // -- Assign roles to users

    @GetMapping
    public APIResponse<String> getAllUsers() {
        log.info("GetAllUsers called");
        return APIResponse.ok("Success");
    }

    //User only tasks
     // -- view own profile
     // -- update own profile




}
