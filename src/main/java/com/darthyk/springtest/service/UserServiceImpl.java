package com.darthyk.springtest.service;

import com.darthyk.springtest.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Override public void createUser(UserDto user) {
        log.info(user.toString());
    }
}
