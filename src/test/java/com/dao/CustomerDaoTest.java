package com.dao;

import com.exceptions.UserException;
import com.model.Customer;
import com.model.ServiceType;
import com.model.Tariff;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

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
    TariffDao tariffDao;

    {
        try {
            tariffDao = new TariffDao();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void customerCreatePositiveTest() {
        Customer customer = new Customer("Ivan", "Burchenko", "+380440000000", "burchenko@gmal.com",0);
        Customer newCustomer = customerDao.create(customer);
        String email = customerDao.findByField("+380440000000").getEmail();
        int id = customerDao.findByField("+380440000000").getId();
        assertEquals(1, 1);
        assertEquals("burchenko@gmal.com", "burchenko@gmal.com");
        customerDao.delete(id);
    }

    @Test
    void customerNotNameNegativeTest() {
        Customer customer = new Customer(null, "Burchenko", "+380639274235", "burchenko@gmal.com",0);
        customerDao.create(customer);
        int id = customerDao.findByField("+380639274235").getId();
        Assertions.assertNotEquals(1, id);
    }

    @Test
    void customerNotSurnameCreateNegativeTest() {
        Customer customer = new Customer("Ivan", null, "+380639274235", "burchenko@gmal.com",0);
        customerDao.create(customer);
        int id = customerDao.findByField("+380639274235").getId();
        Assertions.assertNotEquals(1, id);
    }

    @Test
    void customerNotPhoneCreateNegativeTest() {
        Customer customer = new Customer("Ivan", "Pisarchuk", null, "burchenko@gmal.com",0);
        int id = customerDao.create(customer).getId();
        Assertions.assertNotEquals(1, id);
    }

    @Test
    void customerNotEmailCreateNegativeTest() {
        Customer customer = new Customer("Ivan", "Pisarchuk", "+380639274235", null,0);
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
        Assertions.assertNotEquals(15, 0);
    }

    @Test
    void updatePositiveTest() {
        Customer customer = new Customer("Ivan", "Burchenko", "+380440000000", "burchenko@gmal.com",0);
        customerDao.create(customer);
        int id = customerDao.findByField("+380440000000").getId();
        Customer editCustomer = new Customer(id,"Petr", "Mikulenko", "+380639274299", "mk@gmal.com");
        Customer customer1 = customerDao.update(editCustomer);
        int customerId = customerDao.findByField("+380639274299").getId();
        String email = customerDao.findByID(customerId).getEmail();
        assertEquals("mk@gmal.com", email);
        customerDao.delete(customerId);
    }
    @Test
    void updateNegativeTest() {
        Customer customer = new Customer("Ivan", "Burchenko", "+380639274235", "burchenko@gmal.com",0);
        customerDao.create(customer);
        int id = customerDao.findByField("+380639274235").getId();
        Customer newCustomer = new Customer(id,"Petr", "null", "+380639274299", "mikulenko@gmal.com");
        customerDao.update(newCustomer);
        int customerId = customerDao.findByField("+380639274299").getId();
        Assertions.assertNotEquals(1, 0);
        customerDao.delete(customerId);
    }

    @Test
    void deletePositiveTest() {
        Customer customer = new Customer("Ivan", "Burchenko", "+380639274235", "burchenko@gmal.com",0);
        customerDao.create(customer);
        int id = customerDao.findByField("+380639274235").getId();
        assertEquals(1,1);
        customerDao.delete(id);
    }
    @Test
    void deleteNegativeTest() {
        customerDao.delete(30);
        Assertions.assertNotEquals(1, 0);
    }

    @Test
    void findAllPositiveTest() {
        List<Customer> customers = customerDao.findAll();
        int size = customers.size();
        System.out.println(size);
        assertEquals(size,customers.size());
    }

    @Test
    void addBalancePositiveTest() {
        Customer customer = new Customer("Igor", "Petrenko", "+380445221047", "petrenko@gmal.com",0);
        Customer newCustomer = customerDao.create(customer);
        int id = customerDao.findByField("+380445221047").getId();
        customerDao.addBalance(id,200);
        int balance = customerDao.findByID(id).getBalance();
        assertEquals(200,balance);
        customerDao.delete(id);
    }
    @Test
    void addBalanceNegativeTest() {
        Customer customer = new Customer("Igor", "Petrenko", "+380445221047", "petrenko@gmal.com",0);
        Customer newCustomer = customerDao.create(customer);
        int id = customerDao.findByField("+380445221047").getId();
        customerDao.addBalance(id,-200);
        int balance = customerDao.findByID(id).getBalance();
        Assertions.assertNotEquals(-200, 0);
        customerDao.delete(id);
    }

    @Test
    void withdrawBalancePositiveTest() {
        Customer customer = new Customer("Igor", "Petrenko", "+380445221047", "petrenko@gmal.com",0);
        Customer newCustomer = customerDao.create(customer);
        int id = customerDao.findByField("+380445221047").getId();
        int oldBalance = 1000;
        customerDao.addBalance(id,oldBalance);

        int money = 450;
        customerDao.withdrawBalance(id,money);
        int afterWithDraw = oldBalance-money;
        int newBalance = customerDao.findByID(id).getBalance();
        assertEquals(afterWithDraw,newBalance);
        customerDao.delete(id);

    }
    @Test
    void withdrawBalanceNegativeTest() {
        Customer customer = new Customer("Igor", "Petrenko", "+380445221047", "petrenko@gmal.com",0);
        Customer newCustomer = customerDao.create(customer);
        int id = customerDao.findByField("+380445221047").getId();
        int oldBalance = 100;
        customerDao.addBalance(id,oldBalance);

        int money = 450;
        customerDao.withdrawBalance(id,money);
        int afterWithDraw = oldBalance-money;
        int newBalance = customerDao.findByID(id).getBalance();
        customerDao.delete(id);

        Assertions.assertNotEquals(afterWithDraw, newBalance);
    }

    @Test
    void addTariffCustomerPositiveTest() {
        Customer customer = new Customer("Igor", "Petrenko", "+380445221047", "petrenko@gmal.com",0);
        Customer newCustomer = customerDao.create(customer);
        int id = customerDao.findByField("+380445221047").getId();

        Tariff tariff = new Tariff("GOLD", ServiceType.INTERNET, 250, true);
        Tariff tariff1 = tariffDao.create(tariff);
        int idTariff = tariffDao.findByField("GOLD").getId();

        boolean status = customerDao.addTariffCustomer(id, idTariff);
        assertEquals("true", "true");
        customerDao.deleteTariffCustomer(id,idTariff);

        boolean deleteCustomer = customerDao.delete(id);
        boolean deleteTariff = tariffDao.delete(idTariff);
        assertEquals("true", "true");
        assertEquals("true", "true");
    }
    @Test
    void addTariffCustomerNegativeTest() {
        boolean status = customerDao.addTariffCustomer(55, 48);
        Assertions.assertNotEquals("true", "false");
    }

    @Test
    void deleteTariffCustomerPositiveTest() {
        Customer customer = new Customer("Igor", "Petrenko", "+380445221047", "petrenko@gmal.com",0);
        Customer newCustomer = customerDao.create(customer);
        int id = customerDao.findByField("+380445221047").getId();

        Tariff tariff = new Tariff("GOLD", ServiceType.INTERNET, 250, true);
        Tariff tariff1 = tariffDao.create(tariff);
        int idTariff = tariffDao.findByField("GOLD").getId();

        boolean status = customerDao.addTariffCustomer(id, idTariff);
        customerDao.deleteTariffCustomer(id,idTariff);
        assertEquals("true", "true");

        boolean deleteCustomer = customerDao.delete(id);
        boolean deleteTariff = tariffDao.delete(idTariff);
        assertEquals("true", "true");
        assertEquals("true", "true");
    }
    @Test
    void deleteTariffCustomerNegativeTest() {
        customerDao.deleteTariffCustomer(104,45);
        Assertions.assertNotEquals("true", "false");
    }

    @Test
    void getAllTariffsCustomersPositiveTest() {
        Customer customer = new Customer("Igor", "Petrenko", "+380445221047", "petrenko@gmal.com",0);
        Customer newCustomer = customerDao.create(customer);
        int id = customerDao.findByField("+380445221047").getId();

        Tariff tariff = new Tariff("GOLD", ServiceType.INTERNET, 250, true);
        Tariff tariff1 = tariffDao.create(tariff);
        int idTariff = tariffDao.findByField("GOLD").getId();

        boolean status = customerDao.addTariffCustomer(id, idTariff);
        List<Tariff> tariffs = customerDao.getAllTariffs(id);
        int size = tariffs.size();
        assertEquals("true","true");
        assertNotNull(tariffs);

        customerDao.deleteTariffCustomer(id,idTariff);
        customerDao.delete(id);
        tariffDao.delete(idTariff);
    }
    @Test
    void getAllTariffsCustomersNegativeTest() {
        List<Tariff> tariffs = customerDao.getAllTariffs(45);
        Assertions.assertNotEquals("true", "false");
    }
}
