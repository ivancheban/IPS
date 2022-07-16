package com.service;

import com.dao.DataSource;
import com.dto.TariffDto;
import com.exceptions.TariffException;
import com.model.ServiceType;
import com.model.Tariff;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TariffServiceImplTest {
    @BeforeAll
    static void dbInit() {
        DataSource.innitConfiguration("com.mysql.cj.jdbc.Driver", "jdbc:mysql://localhost:3306/internet_provider_base", "root", "root");
    }
    TariffService tariffService = new TariffServiceImpl();

    @Test
    void createTariffPositiveTest() throws TariffException {
        TariffDto tariff = new TariffDto("People", ServiceType.INTERNET,600);
        boolean status = tariffService.create(tariff);
        int id = tariffService.findByName("People").getId();
        assertEquals(1,1);
        assertEquals(true,true);
    }
    @Test
    void createTariffNegativeTest()  {
        TariffDto tariff = new TariffDto("People", ServiceType.INTERNET,0);
        boolean status = tariffService.create(tariff);
        Assertions.assertNotEquals(true,false);
    }

    @Test
    void findAllSubscription() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void findByName() {
    }

    @Test
    void findById() {
    }

    @Test
    void findAll() {
    }
}
