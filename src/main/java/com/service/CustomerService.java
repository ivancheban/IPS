package com.service;

import com.dao.CustomerDao;
import com.dto.CustomerCreateRequestDto;
import com.dto.CustomerDto;
import com.model.Customer;
import com.model.User;

import java.util.List;

public interface CustomerService {

    Customer findByPhoneNumber(String phone);

    CustomerDto findDyID(int id);

    int promoteCustomer(String phone);

    boolean deleteCustomer(int id);

    List<Customer> findAll();

    boolean addBalance(int customer_id, int money);
}
