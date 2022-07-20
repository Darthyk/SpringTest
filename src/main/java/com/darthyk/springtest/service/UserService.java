package com.darthyk.springtest.service;

import com.darthyk.springtest.dto.UserDto;
import com.darthyk.springtest.model.User;
import java.util.List;

public interface UserService {
    void createUser(UserDto user);
    List<UserDto> getUserByUsername(String username);
    User getUserById(Long id);
    void createSimpleUser(UserDto userDto);
}
