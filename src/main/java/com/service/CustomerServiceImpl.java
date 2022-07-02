package com.service;

import com.dao.CustomerDao;

import com.dao.UserDao;
import com.dto.CustomerCreateRequestDto;
import com.dto.CustomerDto;
import com.dto.UserDto;
import com.exceptions.TariffException;
import com.mapper.BusinessMapper;
import com.model.Customer;
import com.model.User;


import java.sql.SQLException;
import java.util.List;

public class CustomerServiceImpl implements CustomerService{
    CustomerDao customerDao = new CustomerDao();
     public UserDao userDao;

    @Override
    public Customer findByPhoneNumber(String phone) {
        return customerDao.findByField(phone);
    }

    @Override
    public CustomerDto findDyID(int id) {
        Customer customer =customerDao.findByID(id);
        return BusinessMapper.getCustomerDto(customer);
    }

    @Override
    public CustomerDto updateProfile(CustomerDto customerDto) {
        Customer editCustomer = customerDao.update(BusinessMapper.getCustomer(customerDto));
        return BusinessMapper.getCustomerDto(editCustomer);
    }

    @Override
    public boolean deleteCustomer(int id) {
        return customerDao.delete(id);
    }

    @Override
    public List<Customer> findAll() {
        return customerDao.findAll();
    }

    @Override
    public boolean addBalance(int customerId, int money) {
        boolean status = false;
        return customerDao.addBalance(customerId,money);
    }

    @Override
    public boolean withdrawBalance(int customerId, int money) {
        boolean status = false;
        return customerDao.withdrawBalance(customerId,money);
    }

    @Override
    public void addTariffCustomer(int customerId, int tariffId) {
        customerDao.addTariffCustomer(customerId,tariffId);
    }

    @Override
    public void deleteTariffCustomer(int customersId, int tariffId) {
        customerDao.deleteTariffCustomer(customersId,tariffId);
    }
}
