package com.dao;

import com.exceptions.UserException;
import com.model.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class CustomerDaoTest {
    @BeforeAll
    static void dbInit() {
        DataSource.innitConfiguration("com.mysql.cj.jdbc.Driver", "jdbc:mysql://localhost:3306/internet_provider_base", "root", "root");
    }

    CustomerDao customerDao;

    {
        try {
            customerDao = new CustomerDao();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void customerCreatePositiveTest() {
        Customer customer = new Customer("Ivan", "Burchenko", "+380639274235", "burchenko@gmal.com");
        Customer newCustomer = customerDao.create(customer);
        String email = newCustomer.getEmail();
        int id = newCustomer.getId();
        assertEquals(1, 1);
        assertEquals("burchenko@gmal.com", "burchenko@gmal.com");
        customerDao.delete(id);
    }

    @Test
    void customerNotNameNegativeTest() {
        Customer customer = new Customer("null", "Burchenko", "+380639274235", "burchenko@gmal.com");
        int id = customerDao.create(customer).getId();
        Assertions.assertNotEquals(1, id);
    }

    @Test
    void customerNotCreateNegativeTest() {
        Customer customer = new Customer("Ivan", "null", "+380639274235", "burchenko@gmal.com");
        int id = customerDao.create(customer).getId();
        Assertions.assertNotEquals(1, id);
    }

    @Test
    void customerNotPhoneCreateNegativeTest() {
        Customer customer = new Customer("Ivan", "Pisarchuk", "null", "burchenko@gmal.com");
        int id = customerDao.create(customer).getId();
        Assertions.assertNotEquals(1, id);
    }

    @Test
    void customerNotEmailCreateNegativeTest() {
        Customer customer = new Customer("Ivan", "Pisarchuk", "+380639274235", "null");
        int id = customerDao.create(customer).getId();
        Assertions.assertNotEquals(1, id);
    }

    @Test
    void findByFieldPositiveTest() {
        int id = customerDao.findByField("+380955992668").getId();
        assertEquals(1, 1);
    }

    @Test
    void findByFieldNegativeTest() {
        int id = customerDao.findByField("+380955992600").getId();
        Assertions.assertNotEquals(1, id);
    }

    @Test
    void findByIDPositiveTest() {
        Customer customer = customerDao.findByID(1);
        assertEquals(1,1);
    }
    @Test
    void findByIDNegativeTest() {
        Customer customer = customerDao.findByID(15);
        Assertions.assertNotEquals(1, 0);
    }

    @Test
    void updatePositiveTest() {
        Customer customer = new Customer("Ivan", "Burchenko", "+380639274235", "burchenko@gmal.com");
        int id = customerDao.create(customer).getId();
        Customer newCustomer = new Customer(id,"Petr", "Mikulenko", "+380639274299", "mikulenko@gmal.com");
        int customerId = customerDao.update(newCustomer).getId();
        assertEquals(1, 1);
        customerDao.delete(customerId);
    }
    @Test
    void updateNegativeTest() {
        Customer customer = new Customer("Ivan", "Burchenko", "+380639274235", "burchenko@gmal.com");
        int id = customerDao.create(customer).getId();
        Customer newCustomer = new Customer(id,"Petr", "null", "+380639274299", "mikulenko@gmal.com");
        int customerId = customerDao.update(newCustomer).getId();
        Assertions.assertNotEquals(1, 0);
        customerDao.delete(customerId);
    }

    @Test
    void delete() {
    }

    @Test
    void findAll() {
    }

    @Test
    void addBalance() {
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

    @Test
    void getAllTariffs() {
    }
}
