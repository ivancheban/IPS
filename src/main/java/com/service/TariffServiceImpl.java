package com.service;

import com.dao.TariffDao;
import com.dto.TariffDto;
import com.exceptions.TariffException;
import com.mapper.BusinessMapper;
import com.model.Tariff;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class TariffServiceImpl implements TariffService {

    TariffDao tariffDao = new TariffDao();
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
        Tariff tariff = tariffDao.update(BusinessMapper.getTariff(tariffDto));

        return  BusinessMapper.getTariffDto(tariff);
    }

    @Override
    public boolean delete(int id) {

        return tariffDao.delete(id);
    }

    @Override
    public TariffDto findByName(String name) {

        Tariff tariff = tariffDao.findByField(name);

        return BusinessMapper.getTariffDto(tariff);
    }

    @Override
    public TariffDto findById(int id) {
        Tariff tariff = tariffDao.findById(id);

        return BusinessMapper.getTariffDto(tariff);
    }

    @Override
    public List<Tariff> findAll() {


        return tariffDao.findAll();
    }

    @Override
    public List<TariffDto> sortedByPrice() {
        return tariffDao.sortedByPrice().stream().map(tariff -> BusinessMapper.getTariffDto(tariff)).collect(Collectors.toList());
    }

    @Override
    public List<TariffDto> sortedByName() {
        return tariffDao.sortedByName().stream().map(tariff -> BusinessMapper.getTariffDto(tariff)).collect(Collectors.toList());
    }
}
