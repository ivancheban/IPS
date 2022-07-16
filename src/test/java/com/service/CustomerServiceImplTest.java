package com.service;

import com.dao.DataSource;
import com.dto.CustomerDto;
import com.dto.TariffDto;
import com.exceptions.TariffException;
import com.model.Customer;
import com.model.ServiceType;
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
    TariffService tariffService = new TariffServiceImpl();
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
        CustomerDto customer = customerService.findDyID(1);
        assertEquals(1,1);
    }
    @Test
    void findDyIDNegativeTest() {
        CustomerDto customer = customerService.findDyID(15);
        Assertions.assertNotEquals(15,0);
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
    void addBalanceCustomerPositiveTest() {
        Customer customer = new Customer("Andre","Tan","+380501224121","tan@gmail.com",0);
        Customer newCustomer = customerService.create(customer);
        int id = customerService.findByPhoneNumber("+380501224121").getId();
        customerService.addBalance(id,300);
        int balance = customerService.findDyID(id).getBalance();
        assertEquals(300,balance);
        customerService.deleteCustomer(id);
    }
    @Test
    void addBalanceCustomerNegativeTest() {
        customerService.addBalance(33,300);
        Assertions.assertNotEquals(300,0);
    }
    @Test
    void withdrawBalanceCustomerPositiveTest() {
        Customer customer = new Customer("Andre","Tan","+380501224121","tan@gmail.com",0);
        Customer newCustomer = customerService.create(customer);
        int id = customerService.findByPhoneNumber("+380501224121").getId();
        customerService.addBalance(id,300);
        customerService.withdrawBalance(id,200);
        int balance = customerService.findDyID(id).getBalance();
        assertEquals(100,balance);
        customerService.deleteCustomer(id);
    }
    @Test
    void withdrawBalanceCustomerMoneyMoreThanBalanceNegativeTest() {
        Customer customer = new Customer("Andre","Tan","+380501224121","tan@gmail.com",0);
        Customer newCustomer = customerService.create(customer);
        int id = customerService.findByPhoneNumber("+380501224121").getId();
        customerService.addBalance(id,300);
        customerService.withdrawBalance(id,500);
        int balance = customerService.findDyID(id).getBalance();
        System.out.println(balance);
        Assertions.assertNotEquals(-200,300);
        customerService.deleteCustomer(id);
    }
    @Test
    void withdrawBalanceCustomerNegativeNumberTest() {
        Customer customer = new Customer("Andre","Tan","+380501224121","tan@gmail.com",0);
        Customer newCustomer = customerService.create(customer);
        int id = customerService.findByPhoneNumber("+380501224121").getId();
        customerService.addBalance(id,300);
        customerService.withdrawBalance(id,-500);
        int balance = customerService.findDyID(id).getBalance();
        System.out.println(balance);
        Assertions.assertNotEquals(-200,300);
        customerService.deleteCustomer(id);
    }
    @Test
    void withdrawBalanceCustomerNegativeNotCustomerTest() {
        customerService.withdrawBalance(89,100);
        Assertions.assertNotEquals(200,0);
    }
    @Test
    void checkSubscription() {
    }

    @Test
    void addTariffCustomerPositiveTest() throws TariffException {
        Customer customer = new Customer("Andre","Tan","+380501224121","tan@gmail.com",0);
        Customer newCustomer = customerService.create(customer);
        int id = customerService.findByPhoneNumber("+380501224121").getId();
        TariffDto tariff = new TariffDto("Perl", ServiceType.INTERNET,520);
        boolean status =tariffService.create(tariff);
        int idTariff = tariffService.findByName("Perl").getId();
        customerService.addTariffCustomer(id,idTariff);
        assertEquals(1,1);
        assertNotNull(newCustomer);
        assertNotNull(newCustomer);
        assertEquals(true,status);
        customerService.deleteTariffCustomer(id,idTariff);
        customerService.deleteCustomer(id);
        tariffService.delete(idTariff);
    }
    @Test
    void addTariffCustomerNegativeTest() throws TariffException {
        customerService.addTariffCustomer(55,72);
        Assertions.assertNotEquals(1,0);
    }

    @Test
    void deleteTariffCustomerNegativeTest() {
        customerService.deleteTariffCustomer(55,72);
        Assertions.assertNotEquals(1,0);
    }
}
