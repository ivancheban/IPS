package com.service;

import com.dao.UserDao;
import com.dto.UserCreateRequestDto;
import com.dto.UserDto;
import com.exceptions.UserException;
import com.mapper.BusinessMapper;
import com.model.User;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDao();
    private BusinessMapper mapper = new BusinessMapper();

    @Override
    public Map<String, String> registration(UserCreateRequestDto userCreateRequestDto) {

        Map<String, String> validation = userValidation(userCreateRequestDto);


        if (validation.isEmpty()) {

            User user = mapper.conversationRegisterDto(userCreateRequestDto);

            System.out.println("INSIDE SERVICE -> " + user.toString());
            userDao.create(user);
        }
        return validation;


    }
    private Map<String, String> userValidation(UserCreateRequestDto user) {
        Map<String, String> validResult = new TreeMap<>();

        try {
            validatePhoneNumberFormat(user.getPhone());
        } catch (Exception e) {
            validResult.put("phone", e.getMessage());
        }
        try {
            validationPasswordFormat(user.getPassword());
        } catch (Exception e) {
            validResult.put("password", e.getMessage());
        }
        try {
            validConfirmPassword(user.getPassword(), user.getConfirm_password());
        } catch (Exception e) {
            validResult.put("confirm_password", e.getMessage());
        }

        return validResult;

    }



    private void validatePhoneNumberFormat(String phoneNumber) {
        Pattern pattern= Pattern.compile("^\\d{12}$");
        Matcher matcher = pattern.matcher(phoneNumber);
        if(userDao.findByField(phoneNumber).equals(phoneNumber)){
            throw new RuntimeException("It looks like this phone has already been registered");
        }
        if (!matcher.matches()) {
            throw new RuntimeException("You enter invalid phone");
        }
    }

    private void validationPasswordFormat(String password) {
        Pattern pattern = Pattern.compile("^\\d{4}$");
        Matcher matcher = pattern.matcher(password);
        if (!matcher.matches()) {
            throw new RuntimeException("You enter invalid password");
        }
    }
       private  void  validConfirmPassword(String password, String confirm_password){
            if (!password.equals(confirm_password)) {
                throw new RuntimeException("You enter invalid confirm password");
            }
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
        List<User> userList = userDao.findAll();
        return userList;
    }
    }


