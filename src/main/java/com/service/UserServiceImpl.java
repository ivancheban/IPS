package com.service;

import com.dao.UserDao;
import com.dto.UserCreateRequestDto;
import com.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    private  UserDao userDao = new UserDao();

    @Override
    public User registration(UserCreateRequestDto user) {

        return userDao.create(new User(user.getPhone(), user.getPassword()));

    }

    @Override
    public User findByPhoneNumber(String phone) {
        return userDao.findByField(phone);
    }

    @Override
    public int promoteUser(String phone) {
        int status = 0;
        return status;
    }

    @Override
    public boolean deleteUser(int id) {
        return userDao.delete(id);
    }

    @Override
    public List<User> findAll() {
        List<User> userList = userDao.getAllUsers();
        return userList;
    }
}
