package com.service;

import com.dto.UserCreateRequestDto;
import com.dto.UserDto;
import com.model.User;

import java.util.List;
import java.util.Map;

public interface UserService {
   Map <String,String> registration(UserCreateRequestDto user);

    User findByPhoneNumber(String phone);

    int promoteUser(String phone);

    boolean deleteUser(int id);

    List<User> findAll();


}
