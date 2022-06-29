package com.service;

import com.dto.TariffDto;
import com.exceptions.TariffException;
import com.model.Tariff;

import java.util.List;

public interface TariffService {

    boolean create(TariffDto tariffDto) throws TariffException;

    List<TariffDto> findAllSubscription(int customerId);

    TariffDto update(TariffDto tariffDto);

    boolean delete(int id);

    TariffDto findByName(String name);

    TariffDto findById(int id);

    List<Tariff> findAll();

    List<TariffDto> sortedByPrice();

    List<TariffDto> sortedByName();

}
