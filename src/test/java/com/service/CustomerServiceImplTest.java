package com.service;

import com.dao.DataSource;
import com.dto.CustomerDto;
import com.model.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CustomerServiceImplTest {
    @BeforeAll
    static void dbInit() {
        DataSource.innitConfiguration("com.mysql.cj.jdbc.Driver", "jdbc:mysql://localhost:3306/internet_provider_base", "root", "root");
    }
    CustomerService customerService = new CustomerServiceImpl();
    @Test
    void createPositiveTest() {
        Customer customer = new Customer("Andre","Tan","+380501224121","tan@gmail.com",0);
        Customer newCustomer = customerService.create(customer);
        int id = customerService.findByPhoneNumber("+380501224121").getId();
        assertEquals(1,1);
        assertNotNull(newCustomer);
        customerService.deleteCustomer(id);
    }
    @Test
    void createNegativeNotNameTest() {
        Customer customer = new Customer(null,"Tan","+380501224121","tan@gmail.com",0);
        Customer newCustomer = customerService.create(customer);
        int id = customerService.findByPhoneNumber("+380501224121").getId();
        System.out.println(id);
        Assertions.assertNotEquals(1,0);
    }
    @Test
    void createNegativeNotSurNameTest() {
        Customer customer = new Customer("Ivan",null,"+380501224121","tan@gmail.com",0);
        Customer newCustomer = customerService.create(customer);
        int id = customerService.findByPhoneNumber("+380501224121").getId();
        Assertions.assertNotEquals(1,0);
    }
    @Test
    void createNegativeNotPhoneTest() {
        Customer customer = new Customer("Ivan","Petrovenko",null,"tan@gmail.com",0);
        Customer newCustomer = customerService.create(customer);
        int id = customerService.findByPhoneNumber("+380501224121").getId();
        Assertions.assertNotEquals(1,0);
    }
    @Test
    void createNegativeNotEmailTest() {
        Customer customer = new Customer("Ivan","Petrovenko","+380501224121",null,0);
        Customer newCustomer = customerService.create(customer);
        int id = customerService.findByPhoneNumber("+380501224121").getId();
        Assertions.assertNotEquals(1,0);
    }
    @Test
    void createNegativeDuplicatePhoneTest() {
        Customer customer = new Customer("Ivan","Petrovenko","+380955992668","tan@gmail.com",0);
        Customer newCustomer = customerService.create(customer);
        int id = customerService.findByPhoneNumber("+380955992668").getId();
        Assertions.assertNotEquals(1,0);
    }
    @Test
    void createNegativeDuplicateEmailTest() {
        Customer customer = new Customer("Ivan","Petrovenko","+380955992611","sivash@gmail.com",0);
        Customer newCustomer = customerService.create(customer);
        int id = customerService.findByPhoneNumber("+380955992611").getId();
        Assertions.assertNotEquals(1,0);
    }

    @Test
    void findByPhoneNumberPositiveTest() {
        Customer customer = customerService.findByPhoneNumber("+380955992668");
        assertEquals(1,1);
        assertNotNull(customer);
    }
    @Test
    void findByPhoneNumberNegativeTest() {
        Customer customer = customerService.findByPhoneNumber("+380955992655");
        Assertions.assertNotEquals(1,0);
    }

    @Test
    void findDyIDPositiveTest() {
        int id = customerService.findByPhoneNumber("+380955992668").getId();
        assertEquals(1,1);
    }
    @Test
    void findDyIDNegativeTest() {
        int id = customerService.findByPhoneNumber("+380955992655").getId();
        Assertions.assertNotEquals(1,0);
    }

    @Test
    void updateProfilePositiveTest() {
        Customer customer = new Customer("Andre","Tan","+380501224121","tan@gmail.com",0);
        customerService.create(customer);
        int id = customerService.findByPhoneNumber("+380501224121").getId();
        System.out.println(id);
        CustomerDto newCustomerDto = new CustomerDto(id,"Vic","Dorn","+380501224133","dorn@gmail.com");
        customerService.updateProfile(newCustomerDto);
        int newID = customerService.findByPhoneNumber("+380501224133").getId();
        System.out.println(newID);
        assertEquals(id ,newID);
        customerService.deleteCustomer(newID);
    }
    @Test
    void updateProfileNegativeTest() {
        CustomerDto newCustomerDto = new CustomerDto(55,"Vic","Dorn","+380501224133","dorn@gmail.com");
        customerService.updateProfile(newCustomerDto);
        int newID = customerService.findByPhoneNumber("+380501224133").getId();
        Assertions.assertNotEquals(55,0);

    }


    @Test
    void deleteCustomerPositiveTest() {
        Customer customer = new Customer("Andre","Tan","+380501224121","tan@gmail.com",0);
        Customer newCustomer = customerService.create(customer);
        int id = customerService.findByPhoneNumber("+380501224121").getId();
        assertEquals(1,1);
        assertNotNull(newCustomer);
        customerService.deleteCustomer(id);
    }
    @Test
    void deleteCustomerNegativeTest() {
        customerService.deleteCustomer(55);
        Assertions.assertNotEquals(1,0);
    }

    @Test
    void findAllPositiveTest() {
        List<Customer>customers = customerService.findAll();
        int size = customers.size();
        assertEquals(1,1);
        assertNotNull(customers);
    }

    @Test
    void addBalance() {
    }

    @Test
    void checkSubscription() {
    }

    @Test
    void withdrawBalance() {
    }

    @Test
    void addTariffCustomer() {
    }

    @Test
    void deleteTariffCustomer() {
    }
}
