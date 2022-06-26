package com.service;

import com.dto.CustomerCreateRequestDto;
import com.model.Customer;
import com.model.User;

import java.util.List;

public interface CustomerService {


    Customer findByPhoneNumber(String phone);

    int promoteCustomer(String phone);

    boolean deleteCustomer(int id);

    List<Customer> findAll();
}
