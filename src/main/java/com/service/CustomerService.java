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

    CustomerDto updateProfile(CustomerDto customerDto);

    boolean deleteCustomer(int id);

    List<Customer> findAll();

    boolean addBalance(int customerId, int money);

   boolean withdrawBalance(int customerId, int money);

    void addTariffCustomer(int customerId, int tariffId);

    void deleteTariffCustomer(int customersId, int tariffId);

    boolean checkSubscription(int customerId, int tariffId);
}
