package com.service;

import com.dao.CustomerDao;
import com.dao.DataSource;
import com.dao.UserDao;
import com.dto.CustomerCreateRequestDto;
import com.mapper.BusinessMapper;
import com.model.Customer;
import com.model.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
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
            Customer customer = BusinessMapper.conversationRegisterDto(customerCreateRequestDto);

            System.out.println("INSIDE SERVICE -> " + user.toString());

            userDao.create(user);
            customerDao.create(customer);

        }
        return validation;

    }

    private List<String> userValidation(CustomerCreateRequestDto createRequestDto) {
        List<String> validResult = new ArrayList<>();

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
        Pattern pattern = Pattern.compile("^\\d{12}$");
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
        List<User> usersList = userDao.findAll();
        return usersList;
    }
}


