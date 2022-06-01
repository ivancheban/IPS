package com.dao;

import com.dto.CustomerCreateRequestDto;
import com.model.Customer;
import com.model.User;
import com.service.UserServiceImpl;

import java.sql.Connection;
import java.sql.SQLException;

public class TestClass {
    public static void main(String[] args) {
        CustomerDao customerDao = null;
        UserDao userDao = null;
        Connection connection;
        try {
            connection = DataSource.getConnection();
             userDao = new UserDao(connection);
             customerDao = new CustomerDao(connection);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        UserServiceImpl userService = new UserServiceImpl();
        String name = "Igor";
        String surname = "Timoshenko";
        String phoneNumber = "+380952356459";
        String email = "borysko@gmail.com";
        String password = "123456Q@q";
        String confirmPassword = "123456Q@q";

        CustomerCreateRequestDto customerCreateRequestDto = new CustomerCreateRequestDto(name, surname, email, phoneNumber, password, confirmPassword);
        userService.registration(customerCreateRequestDto);

//        User user = new User("380662315698","1233");
//        userDao.create(user);
//
//        customerDao.create(new Customer("andrew","Wayna","380507097355","andrew@com.ua"));

    }
}
