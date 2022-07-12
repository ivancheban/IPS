package com.dao;

import com.exceptions.TariffException;
import com.model.ServiceType;
import com.model.Tariff;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

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

    @Test
    void createTariffPositiveTest()  {
        Tariff tariff = new Tariff("GOLD", ServiceType.INTERNET,250,true);
        Tariff tariff1 = tariffDao.create(tariff);
        int id = tariffDao.findByField("GOLD").getId();
        assertEquals(1,1);
        tariffDao.delete(id);
    }
    @Test
    void createTariffDuplicateNameNegativeTest()  {
        Tariff tariff = new Tariff("NIGHT", ServiceType.INTERNET,250,true);
        Tariff tariff1 = tariffDao.create(tariff);
        int id = tariffDao.findByField("NIGHT").getId();
        Assertions.assertNotEquals(1, id);
    }
    @Test
    void createTariffNotNameNegativeTest()  {
        Tariff tariff = new Tariff(null, ServiceType.INTERNET,250,true);
        Tariff tariff1 = tariffDao.create(tariff);
        int id = tariffDao.findByField("GOLD").getId();
        Assertions.assertNotEquals(1, id);
    }
    @Test
    void createTariffNotTypeNegativeTest()  {
        Tariff tariff = new Tariff("GOLD", null,250,true);
        Tariff tariff1 = tariffDao.create(tariff);
        int id = tariffDao.findByField("GOLD").getId();
        Assertions.assertNotEquals(1, id);
    }
    @Test
    void createTariffNotPriceNegativeTest()  {
        Tariff tariff = new Tariff("GOLD", ServiceType.INTERNET,0,true);
        Tariff tariff1 = tariffDao.create(tariff);
        int id = tariffDao.findByField("GOLD").getId();
        Assertions.assertNotEquals(1, id);
    }

    @Test
    void findByField() {
    }

    @Test
    void findById() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void findAll() {
    }
    @Test
    void getAllSubscribedTariffs() {
    }
}
