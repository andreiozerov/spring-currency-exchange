package com.example.springsecurity.service;

import com.example.springsecurity.dto.UserDto;
import com.example.springsecurity.entity.User;

public interface UserService {
    User registerNewUserAccount(UserDto userDto);
}
