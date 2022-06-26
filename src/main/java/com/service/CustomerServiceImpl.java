package com.service;

import com.dao.CustomerDao;

import com.mapper.BusinessMapper;
import com.model.Customer;


import java.util.List;

public class CustomerServiceImpl implements CustomerService{
    CustomerDao customerDao = new CustomerDao();
    private BusinessMapper businessMapper;

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public Customer findByPhoneNumber(String phone) {
        return customerDao.findByField(phone);
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
}
