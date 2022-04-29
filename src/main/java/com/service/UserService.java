package com.service;

import com.dto.UserCreateRequestDto;
import com.model.User;

import java.util.List;

public interface UserService {
    String registration(UserCreateRequestDto user);

    User findByPhoneNumber(String phone);

    int promoteUser(String phone);

    boolean deleteUser(int id);

    List<User> findAll();


}
