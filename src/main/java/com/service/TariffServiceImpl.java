package com.service;

import com.dao.TariffDao;
import com.model.Tariff;

import java.util.List;

public class TariffServiceImpl implements TariffService{

    TariffDao tariffDao = new TariffDao();

    @Override
    public Tariff create(Tariff tariff) {

        Tariff newTariff = tariffDao.create(tariff);

        return tariff;
    }

    @Override
    public Tariff update(Tariff tariff) {
        Tariff updateTariff = tariffDao.update(tariff);
        return updateTariff;
    }

    @Override
    public boolean delete(int id) {

        return tariffDao.delete(id);
    }

    @Override
    public Tariff findByName(String name) {
        Tariff tariff = tariffDao.findByField(name);
        return tariff;
    }

    @Override
    public List<Tariff> findAll() {
       List <Tariff> tariffs = tariffDao.findAll();
        return tariffs;
    }
}
