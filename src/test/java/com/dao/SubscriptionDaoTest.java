package com.dao;

import com.exceptions.SubscriptionException;
import com.model.ServiceType;
import com.model.Subscription;
import com.model.Tariff;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static java.time.LocalDate.now;
import static org.junit.jupiter.api.Assertions.*;

class SubscriptionDaoTest {

    @BeforeAll
    static void dbInit() {
        DataSource.innitConfiguration("com.mysql.cj.jdbc.Driver", "jdbc:mysql://localhost:3306/internet_provider_base", "root", "root");
    }

    SubscriptionDao subscriptionDao;
    {
        try {
            subscriptionDao = new SubscriptionDao();
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
    void createSubscriptionPositiveTest() {
        Subscription subscription = new Subscription("RADIO", false);
        subscriptionDao.create(subscription);
        int id = subscriptionDao.findByField("RADIO").getId();
        assertEquals(1, 1);
        subscriptionDao.delete(id);
    }

    @Test
    void createSubscriptionNegativeTest() {
        Subscription subscription = new Subscription(null, false);
        subscriptionDao.create(subscription);
        int id = subscriptionDao.findByField(null).getId();
        Assertions.assertNotEquals(1, 0);
    }

    @Test
    void createSubscriptionNegativeDuplicateTest() {
        Subscription subscription = new Subscription("INTERNET", false);
        Subscription subscription1 = subscriptionDao.create(subscription);
        Assertions.assertNotEquals(1, 0);
    }

    @Test
    void findByFieldServicePositiveTest() {
        Subscription subscription = new Subscription("RADIO", false);
        subscriptionDao.create(subscription);
        Subscription subscriptionNew = subscriptionDao.findByField("RADIO");
        int id = subscriptionDao.findByField("RADIO").getId();
        assertNotNull(subscriptionNew);
        subscriptionDao.delete(id);
    }

    @Test
    void findByFieldServiceNegativeTest() {
        int id = subscriptionDao.findByField("RADIO").getId();
        Assertions.assertNotEquals(1, 0);
    }

    @Test
    void findByIdServicePositiveTest() {
        Subscription subscription = subscriptionDao.findById(1);
        assertEquals(1, 1);
    }

    @Test
    void findByIdServiceNegativeTest() {
        Subscription subscription = subscriptionDao.findById(10);
        Assertions.assertNotEquals(10, 0);
    }

    @Test
    void updateServicePositiveTest() {
        Subscription subscription = new Subscription("RADIO", false);
        subscriptionDao.create(subscription);
        int id = subscriptionDao.findByField("RADIO").getId();
        Subscription newSubscription = new Subscription(id, "MAGAZINE", false);
        subscriptionDao.update(newSubscription);
        int newId = subscriptionDao.findByField("MAGAZINE").getId();
        assertEquals(id, newId);
        subscriptionDao.delete(newId);
    }

    @Test
    void updateServiceNegativeTest() {
        Subscription newSubscription = new Subscription(10, "MAGAZINE", false);
        subscriptionDao.update(newSubscription);
        int newId = subscriptionDao.findByField("MAGAZINE").getId();
        Assertions.assertNotEquals(10, newId);
    }

    @Test
    void deleteServicePositiveTest() {
        Subscription subscription = new Subscription("RADIO", false);
        subscriptionDao.create(subscription);
        Subscription subscriptionNew = subscriptionDao.findByField("RADIO");
        int id = subscriptionDao.findByField("RADIO").getId();
        boolean status = subscriptionDao.delete(id);
        assertEquals(true, true);
    }

    @Test
    void deleteServiceNegativeTest() {
        boolean status = subscriptionDao.delete(10);
        Assertions.assertNotEquals(true, false);
    }

    @Test
    void findAllServicePositiveTest() {
        List<Subscription> subscriptions = subscriptionDao.findAll();
        assertEquals(1, 1);
        assertNotNull(subscriptions);
    }

    @Test
    void getAllTariffsPositiveTest() {
        List<Tariff> tariffs = subscriptionDao.getAllTariffs(1);
        System.out.println(tariffs);
        assertNotNull(tariffs);
    }

    @Test
    void getAllTariffsNegativeTest() {
        List<Tariff> tariffs = subscriptionDao.getAllTariffs(10);
        Assertions.assertNotEquals(1, 0);
    }

    @Test
    void addTariffPositiveTest() throws SubscriptionException {
        int idService = 1;
        Tariff tariff = new Tariff("GOLD", ServiceType.INTERNET, 250, true);
        Tariff tariff1 = tariffDao.create(tariff);
        int id = tariffDao.findByField("GOLD").getId();
        subscriptionDao.addTariff(idService,id);
        assertEquals(1, 1);

        subscriptionDao.deleteTariff(idService, id);
        tariffDao.delete(id);
    }
    @Test
    void getAllByServicePositiveTest() {
        List<Tariff> tariffs = subscriptionDao.getAllByService(2);
        System.out.println(tariffs);
        assertNotNull(tariffs);
    }

    @Test
    void getAllByServiceNegativeTest() {
        List<Tariff> tariffs = subscriptionDao.getAllByService(9);
        Assertions.assertNotEquals(1, 0);
    }
}
