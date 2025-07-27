package com.abdul.ums.service.impl;

import com.abdul.ums.dto.UserDto;
import com.abdul.ums.dto.UserRequest;
import com.abdul.ums.entity.Role;
import com.abdul.ums.entity.User;
import com.abdul.ums.enums.RoleEnum;
import com.abdul.ums.exception.NoRoleFoundException;
import com.abdul.ums.exception.NoUserFoundException;
import com.abdul.ums.exception.UserAlreadyExistException;
import com.abdul.ums.repository.RoleRepository;
import com.abdul.ums.repository.UserRepository;
import com.abdul.ums.service.UmsService;
import com.abdul.ums.utils.UtilMethods;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static com.abdul.ums.constant.AppConstant.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class UmsServiceImpl implements UmsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @SneakyThrows
    @Override
    public List<UserDto> getAllUsers() {
        log.info("Getting all users");
        List<User> userList = userRepository.findAll();
        log.info("user list size => {}", userList.size());
        if (userList.isEmpty()) throw new NoUserFoundException(NO_USER_FOUND_CODE, NO_USER_FOUND_MESSAGE);
        return userList.stream().map(UserDto::fromUser).collect(Collectors.toList());
    }

    @SneakyThrows
    @Override
    public UserDto addUser(UserRequest userRequest) {
        User user = User.from(userRequest.getName(), userRequest.getEmail(), userRequest.getPassword(), Set.of(getCustomRole(RoleEnum.ROLE_USER)));
        try {
            userRepository.save(user);
        } catch (Exception ex) {
            throw new UserAlreadyExistException(USER_ALREADY_EXIST_CODE, USER_ALREADY_EXIST_MESSAGE);
        }
        return UserDto.fromUser(user);
    }

    @SneakyThrows
    @Override
    public UserDto deleteUser(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            userRepository.delete(user.get());
            return UserDto.fromUser(user.get());
        }
        throw new NoUserFoundException(NO_USER_FOUND_CODE, NO_USER_FOUND_MESSAGE);
    }

    @SneakyThrows
    @Override
    public UserDto updateUser(String email, String password, String name) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            user.get().setName(name);
            user.get().setPassword(password);
            userRepository.save(user.get());
            return UserDto.fromUser(user.get());
        }
        throw new NoUserFoundException(NO_USER_FOUND_CODE, NO_USER_FOUND_MESSAGE);
    }

    @SneakyThrows
    @Override
    public UserDto updateRole(String email, List<String> roleName) {
        List<Role> roleList = roleName.stream().map(role -> roleRepository.findByName(role).orElseThrow(() -> new NoRoleFoundException(NO_ROLE_FOUND_CODE, NO_ROLE_FOUND_MESSAGE))).collect(Collectors.toList());
        Optional<User> currentUser = userRepository.findByEmail(email);
        if (currentUser.isPresent()) {
            currentUser.get().getRoles().clear();
            currentUser.get().getRoles().addAll(roleList);
            userRepository.save(currentUser.get());
            return UserDto.fromUser(currentUser.get());
        }
        throw new NoUserFoundException(NO_USER_FOUND_CODE, NO_USER_FOUND_MESSAGE);
    }

    private Role getCustomRole(RoleEnum roleEnum) {
        return roleRepository.findByName(roleEnum.name()).get();
    }
}
