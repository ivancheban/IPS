package com.dao;

import com.dto.CustomerCreateRequestDto;
import com.dto.TariffDto;
import com.model.Customer;
import com.model.Tariff;
import com.model.User;
import com.service.TariffService;
import com.service.TariffServiceImpl;
import com.service.UserServiceImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class TestClass {
    public static void main(String[] args) {
        TariffDao tariffDao = new TariffDao();
        TariffService tariffService = new TariffServiceImpl();
//        Tariff tariff = tariffDao.findById(4);
//        System.out.println(tariff);
        List<Tariff> list = tariffDao.getAllSubscribedTariffs(20);
        List<TariffDto> list2 = tariffService.findAllSubscription(20);
        for (TariffDto t : list2){
            System.out.println(t);
        }

    }
}
