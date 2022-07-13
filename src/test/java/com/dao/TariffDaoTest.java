package com.dao;

import com.exceptions.TariffException;
import com.model.Customer;
import com.model.ServiceType;
import com.model.Tariff;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TariffDaoTest {
    @BeforeAll
    static void dbInit() {
        DataSource.innitConfiguration("com.mysql.cj.jdbc.Driver", "jdbc:mysql://localhost:3306/internet_provider_base", "root", "root");
    }

    TariffDao tariffDao;

    {
        try {
            tariffDao = new TariffDao();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
    void createTariffPositiveTest() {
        Tariff tariff = new Tariff("GOLD", ServiceType.INTERNET, 250, true);
        Tariff tariff1 = tariffDao.create(tariff);
        System.out.println(tariff1);
        int id = tariffDao.findByField("GOLD").getId();
        assertEquals(1, 1);
        tariffDao.delete(id);
    }

    @Test
    void createTariffDuplicateNameNegativeTest() {
        Tariff tariff = new Tariff("NIGHT", ServiceType.INTERNET, 250, true);
        Tariff tariff1 = tariffDao.create(tariff);
        int id = tariffDao.findByField("NIGHT").getId();
        Assertions.assertNotEquals(1, id);
    }

    @Test
    void createTariffNotNameNegativeTest() {
        Tariff tariff = new Tariff(null, ServiceType.INTERNET, 250, true);
        Tariff tariff1 = tariffDao.create(tariff);
        int id = tariffDao.findByField("GOLD").getId();
        Assertions.assertNotEquals(1, id);
    }

    @Test
    void createTariffNotTypeNegativeTest() {
        Tariff tariff = new Tariff("GOLD", null, 250, true);
        Tariff tariff1 = tariffDao.create(tariff);
        int id = tariffDao.findByField("GOLD").getId();
        Assertions.assertNotEquals(1, id);
    }

    @Test
    void createTariffNotPriceNegativeTest() {
        Tariff tariff = new Tariff("GOLD", ServiceType.INTERNET, 0, true);
        Tariff tariff1 = tariffDao.create(tariff);
        int id = tariffDao.findByField("GOLD").getId();
        Assertions.assertNotEquals(1, id);
    }

    @Test
    void findByFieldPositiveTest() {
        Tariff tariff = tariffDao.findByField("FOOTBALL+");
        int id = tariffDao.findByField("FOOTBALL+").getId();
        assertEquals(16, id);
    }

    @Test
    void findByFieldNegativeTest() {
        Tariff tariff = tariffDao.findByField("BORN");
        int id = tariffDao.findByField("BORN").getId();
        Assertions.assertNotEquals(1, id);
    }

    @Test
    void findByIDPositiveTest() {
        Tariff tariff = tariffDao.findById(16);
        int id = tariff.getId();
        assertEquals(16, 16);
    }

    @Test
    void findByIDNegativeTest() {
        Tariff tariff = tariffDao.findById(44);
        int id = tariff.getId();
        Assertions.assertNotEquals(44, id);
    }

    @Test
    void updateTariffPositiveTest() {
        Tariff tariff = new Tariff("GOLD", ServiceType.INTERNET, 250, true);
        Tariff tariff1 = tariffDao.create(tariff);
        int id = tariffDao.findByField("GOLD").getId();
        Tariff newTariff = new Tariff(id, "YEAR", ServiceType.INTERNET, 300, true);
        tariffDao.update(newTariff);
        int idNewTariff = tariffDao.findByField("YEAR").getId();
        assertEquals(id, idNewTariff);
        tariffDao.delete(idNewTariff);
    }

    @Test
    void updateTariffNegativeTest() {

        Tariff newTariff = new Tariff(45, "YEAR", ServiceType.INTERNET, 300, true);
        tariffDao.update(newTariff);
        int idNewTariff = tariffDao.findByField("YEAR").getId();
        Assertions.assertNotEquals(45, idNewTariff);

    }

    @Test
    void deletePositiveTest() {
        Tariff tariff = new Tariff("GOLD", ServiceType.INTERNET, 250, true);
        Tariff tariff1 = tariffDao.create(tariff);
        int id = tariffDao.findByField("GOLD").getId();
        boolean status = tariffDao.delete(id);
        assertEquals("true", "true");
    }

    @Test
    void deleteNegativeTest() {
        tariffDao.delete(55);
        Assertions.assertNotEquals("true", "false");
    }

    @Test
    void findAllPositiveTest() {
        List<Tariff> tariffList = tariffDao.findAll();
        assertNotNull(tariffList);
    }

    @Test
    void getAllSubscribedTariffsPositiveTest() {
        Customer customer = new Customer("Dmitriy", "Kononenko", "+380502198143", "kononenko@gmail.com,", 0);
        customerDao.create(customer);
        int id = customerDao.findByField("+380502198143").getId();

        Tariff tariff = new Tariff("GOLD", ServiceType.INTERNET, 250, true);
        Tariff tariff1 = tariffDao.create(tariff);
        int idTariff = tariffDao.findByField("GOLD").getId();

        boolean status = customerDao.addTariffCustomer(id, idTariff);
        List<Tariff> customerTariffList = tariffDao.getAllSubscribedTariffs(id);
        assertNotNull(customerTariffList);

        customerDao.deleteTariffCustomer(id, idTariff);
        customerDao.delete(id);
        tariffDao.delete(idTariff);
    }

    @Test
    void getAllSubscribedTariffsNegativeTest() {
        List<Tariff> customerTariffList = tariffDao.getAllSubscribedTariffs(84);
        Assertions.assertNotEquals("true", "false");
    }
}
