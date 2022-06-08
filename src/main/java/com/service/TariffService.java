package com.service;

import com.dto.TariffDto;
import com.exceptions.TariffException;
import com.model.Tariff;

import java.util.List;

public interface TariffService {

    boolean create(TariffDto tariffDto) throws TariffException;

    TariffDto update(TariffDto tariffDto);

    boolean delete(int id);

    TariffDto findByName(String name);

    List<TariffDto> findAll();

}
