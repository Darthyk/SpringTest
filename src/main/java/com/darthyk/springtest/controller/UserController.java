package com.darthyk.springtest.controller;

import com.darthyk.springtest.dto.UserDto;
import com.darthyk.springtest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public String createUser(@RequestBody UserDto user) {
        //userService.createUser(user);
        userService.createSimpleUser(user);
        return "OK";
    }
}
