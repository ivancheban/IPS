package com.service;

import com.dto.CustomerCreateRequestDto;
import com.model.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    List<String> registration(CustomerCreateRequestDto createRequestDto);

    User findByPhoneNumber(String phone);

    int promoteUser(String phone);

    boolean deleteUser(int id);

    List<User> findAll();


}
