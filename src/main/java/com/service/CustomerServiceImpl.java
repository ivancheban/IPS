package com.service;

import com.dao.CustomerDao;

import com.dto.CustomerDto;
import com.mapper.BusinessMapper;
import com.model.Customer;


import java.util.List;

public class CustomerServiceImpl implements CustomerService{
    CustomerDao customerDao = new CustomerDao();
    @Override
    public Customer findByPhoneNumber(String phone) {
        return customerDao.findByField(phone);
    }

    @Override
    public CustomerDto findDyID(int id) {
        Customer customer =customerDao.findByID(id);
        return new CustomerDto(BusinessMapper.getCustomerDto(customer));
    }

    @Override
    public int promoteCustomer(String phone) {
        return 0;
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
    public boolean addBalance(int customer_id, int money) {
        boolean status = false;
        return customerDao.addBalance(customer_id,money);
    }
}
