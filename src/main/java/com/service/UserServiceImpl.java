package com.service;

import com.dao.UserDao;
import com.dto.UserCreateRequestDto;
import com.exceptions.UserException;
import com.mapper.BusinessMapper;
import com.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDao();
    private BusinessMapper mapper = new BusinessMapper();

    @Override
    public String registration(UserCreateRequestDto user) {


        String userValidate = userValidation(user);

        if (userValidate.equals("successfully")) {
            User user1 = mapper.conversationRegisterDto(user);


            if (userDao.create(user1) == null) {
                userValidate = "error";
          throw new UserException("user is not create");
            }
        }
        return userValidate;


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

    private String userValidation(UserCreateRequestDto user) {

        if (user.getPhone() == null || user.getPhone().isEmpty()) {
            return "PhoneNumber is not found";
        } else if (!validatePhoneNumberFormat(user.getPhone())) {
            return "This is phone number not correct";
        } else if (userDao.findByField(user.getPhone()).getPhone() != null) {
            return "This is phone number already exist";
        } else if (user.getPassword() == null || user.getPassword().isEmpty()) {
            return "You are  not enter password not correct";
        } else if (user.getPassword().length() < 4) {
            return "You are  not enter password not correct";
        }
        else if (!user.getPassword().equals(user.getCondPassword())) {
            return "You are make mistake when trying to repeat password";
        }

        return "successfully";

    }

    private boolean validatePhoneNumberFormat(String phoneNumber) {
        String regexPhone = "^\\d{12}$";
        return phoneNumber.matches(regexPhone);
    }

    private boolean validationPasswordFormat(String password) {
        String regexPassword = "^\\d{4}$";
        return password.matches(regexPassword);
    }
}


