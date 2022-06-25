package com.service;

import com.dto.CustomerCreateRequestDto;
import com.dto.UserDto;
import com.model.Role;
import com.model.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    List<String> registration(CustomerCreateRequestDto createRequestDto);

    UserDto findByPhoneNumber(String phone);

    UserDto findById(int id);

    int promoteUser(String phone);

    boolean deleteUser(int id);

    List<User> findAll();

    Role identy(String phoneNumber, String password);

    UserDto update(UserDto userDto);

}
