package com.abdul.ums.controller;

import com.abdul.ums.dto.UserDto;
import com.abdul.ums.dto.UserRequest;
import com.abdul.ums.model.APIResponse;
import com.abdul.ums.service.UmsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/ums")
@Slf4j
@RequiredArgsConstructor
public class UmsController {

    private final UmsService umsService;

    @GetMapping
    public APIResponse<List<UserDto>> getAllUsers() {
        log.info("GetAllUsers called");
        return APIResponse.ok(umsService.getAllUsers());
    }

    @PostMapping
    public APIResponse<UserDto> addUser(@RequestBody UserRequest userRequest) {
        log.info("addUser called");
        return APIResponse.ok(umsService.addUser(userRequest));
    }

    @DeleteMapping
    public APIResponse<UserDto> deleteUser(@RequestParam String email) {
        log.info("deleteUser called");
        return APIResponse.ok(umsService.deleteUser(email));
    }

    @PutMapping
    public APIResponse<UserDto> updateUser(@RequestParam String email,@RequestParam String password,@RequestParam String name) {
        log.info("updateUser called");
        return APIResponse.ok(umsService.updateUser(email,password,name));
    }

    @PutMapping("update-role")
    public APIResponse<UserDto> updateRole(@RequestParam String email,@RequestParam List<String> roleName) {
        log.info("updateRole called");
        return APIResponse.ok(umsService.updateRole(email,roleName));
    }


    //User only tasks
     // -- view own profile
     // -- update own profile




}
