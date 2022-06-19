package com.service;

import com.dao.TariffDao;
import com.dto.TariffDto;
import com.exceptions.TariffException;
import com.mapper.BusinessMapper;
import com.model.Tariff;


import java.util.List;


public class TariffServiceImpl implements TariffService {

    TariffDao tariffDao = new TariffDao();
    private BusinessMapper businessMapper;

    @Override
    public boolean create(TariffDto tariffDto) throws TariffException {

        Tariff newTariff = BusinessMapper.convertTariff(tariffDto);

        tariffDao.create(newTariff);

        if (newTariff.getId() == 0) {

            throw new TariffException("tariff is not create");
        }

        return true;
    }

    @Override
    public TariffDto update(TariffDto tariffDto) {
        Tariff tariff = tariffDao.update(businessMapper.getTariff(tariffDto));

        return  businessMapper.getTariffDto(tariff);
    }

    @Override
    public boolean delete(int id) {

        return tariffDao.delete(id);
    }

    @Override
    public TariffDto findByName(String name) {

        Tariff tariff = tariffDao.findByField(name);

        return businessMapper.getTariffDto(tariff);
    }

    @Override
    public TariffDto findById(int id) {
        Tariff tariff = tariffDao.findById(id);

        return businessMapper.getTariffDto(tariff);
    }

    @Override
    public List<Tariff> findAll() {


        return tariffDao.findAll();
    }
}
