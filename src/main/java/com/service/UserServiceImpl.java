package com.service;

import com.dao.CustomerDao;
import com.dao.DataSource;
import com.dao.UserDao;
import com.dto.CustomerCreateRequestDto;
import com.dto.UserDto;
import com.mapper.BusinessMapper;
import com.model.Customer;
import com.model.Role;
import com.model.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserServiceImpl implements UserService {

    private UserDao userDao;
    private CustomerDao customerDao;
    private Connection connection;
    private BusinessMapper businessMapper;

    {
        try {
            connection = DataSource.getConnection();
            userDao = new UserDao(connection);
            customerDao = new CustomerDao(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<String> registration(CustomerCreateRequestDto customerCreateRequestDto) {
        List<String> validation = userValidation(customerCreateRequestDto);

        if (validation.isEmpty()) {

            User user = BusinessMapper.conversationRegisterUserDto(customerCreateRequestDto);
            user.setRole(Role.CLIENT);
            Customer customer = BusinessMapper.conversationRegisterDto(customerCreateRequestDto);

          userDao.create(user);
          customerDao.create(customer);
        }
        return validation;
    }

    private List<String> userValidation(CustomerCreateRequestDto createRequestDto) {
        List<String> validResult = new ArrayList<>();
        try {
            validFormatName(createRequestDto.getName());
        } catch (Exception e) {
            validResult.add("name");
        }
        try {
            validFormatSurName(createRequestDto.getSurname());
        } catch (Exception e) {
            validResult.add("surname");
        }


        try {
            validatePhoneNumberFormat(createRequestDto.getPhone());
        } catch (Exception e) {
            validResult.add("phone");
        }
        try {
            validationPasswordFormat(createRequestDto.getPassword());
        } catch (Exception e) {
            validResult.add("password");
        }
        try {
            validConfirmPassword(createRequestDto.getPassword(), createRequestDto.getConfirm_password());
        } catch (Exception e) {
            validResult.add("confirm_password");
        }

        return validResult;

    }


    private void validatePhoneNumberFormat(String phoneNumber) {
        Pattern pattern = Pattern.compile("^\\+?(38)?(\\d{10,11})$");
        Matcher matcher = pattern.matcher(phoneNumber);
        if (userDao.findByField(phoneNumber).equals(phoneNumber)) {
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

    private void validConfirmPassword(String password, String confirm_password) {
        if (!password.equals(confirm_password)) {
            throw new RuntimeException("You enter invalid confirm password");
        }
    }
    private void validFormatName(String name) {
        Pattern pattern = Pattern.compile("^[а-яА-ЯёЁa-zA-Z0-9]+$");
        Matcher matcher = pattern.matcher(name);

        if (!matcher.matches()) {
            throw new RuntimeException("You enter invalid name");
        }

    }
    private void validFormatSurName(String surname) {
        Pattern pattern = Pattern.compile( "^[а-яА-ЯёЁa-zA-Z0-9]+$");
        Matcher matcher = pattern.matcher(surname);

        if (!matcher.matches()) {
            throw new RuntimeException("You enter invalid surname ");
        }

    }

    @Override
    public UserDto findByPhoneNumber(String phone) {
        User user = userDao.findByField(phone);
        return BusinessMapper.getUserDto(user);
    }

    @Override
    public UserDto findById(int id) {
        User user = userDao.findById(id);
        return BusinessMapper.getUserDto(user);
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
        List<User> usersList = userDao.findAll();
        System.out.println("Get All Users" + usersList);

        return usersList;
    }

    @Override
    public Role identy(String phoneNumber, String password) {
        UserDto user = findByPhoneNumber(phoneNumber);
        if (user != null){
            if(user.getPassword().equals(password)){
                return user.getRole();
            }
        }
        return null;
    }

    @Override
    public UserDto update(UserDto userDto) {
        User userEdit = userDao.update(BusinessMapper.getUser(userDto));
        return BusinessMapper.getUserDto(userEdit) ;
    }
}


