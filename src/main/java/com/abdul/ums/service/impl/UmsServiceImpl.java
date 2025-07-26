package com.abdul.ums.service.impl;

import com.abdul.ums.dto.UserDto;
import com.abdul.ums.entity.User;
import com.abdul.ums.exception.NoUserFoundException;
import com.abdul.ums.repository.UserRepository;
import com.abdul.ums.service.UmsService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.abdul.ums.constant.AppConstant.NO_USER_FOUND_CODE;
import static com.abdul.ums.constant.AppConstant.NO_USER_FOUND_MESSAGE;

@Service
@RequiredArgsConstructor
@Slf4j
public class UmsServiceImpl implements UmsService {

    private final UserRepository userRepository;

    @SneakyThrows
    @Override
    public List<UserDto> getAllUsers() {
        log.info("Getting all users");
        List<User> userList = userRepository.findAll();
        log.info("user list size => {}",userList.size());
        if (userList.isEmpty())
            throw new NoUserFoundException(NO_USER_FOUND_CODE,NO_USER_FOUND_MESSAGE);
        return userList.stream().map(UserDto::fromUser).collect(Collectors.toList());
    }
}
