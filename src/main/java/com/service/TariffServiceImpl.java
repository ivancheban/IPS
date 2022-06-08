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
        System.out.println(newTariff + " before service");
        Tariff newTariff1 = tariffDao.create(newTariff);
        System.out.println(newTariff1 + "after service");
        if (newTariff.getId() == 0) {
            System.out.println(newTariff.getId());
            throw new TariffException("tariff is not create");
        }

        return true;
    }

    @Override
    public TariffDto update(TariffDto tariffDto) {
//        TariffDto updateTariff = tariffDao.update(tariffDto);
        return null;
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
    public List<TariffDto> findAll() {
        //List <TariffDto> tariffs = tariffDao.findAll();
        return null;
    }
}
