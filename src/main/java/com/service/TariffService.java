package com.service;

import com.model.Tariff;

import java.util.List;

public interface TariffService {

    Tariff create(Tariff tariff);

    Tariff update(Tariff tariff);

    boolean delete(int id);

    Tariff findByName(String name);

    List<Tariff> findAll();

}
