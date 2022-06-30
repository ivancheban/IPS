package com.dao;

import com.dto.CustomerCreateRequestDto;
import com.dto.TariffDto;
import com.model.Customer;
import com.model.Tariff;
import com.model.User;
import com.service.TariffService;
import com.service.TariffServiceImpl;
import com.service.UserServiceImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class TestClass {
    public static void main(String[] args) {
        //16
       CustomerDao customerDao = new CustomerDao();
       customerDao.deleteTariffCustomer(16, 1);

    }
}
