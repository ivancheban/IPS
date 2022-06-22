package com.service;

import com.dto.SubscriptionDto;

import com.exceptions.SubscriptionException;


import com.model.Subscription;
import com.model.Tariff;


import java.util.List;

public interface SubscriptionService {

    boolean create(SubscriptionDto subscriptionDto) throws SubscriptionException;

    SubscriptionDto update(SubscriptionDto subscriptionDto);

    boolean delete(int id);

    SubscriptionDto findByName(String value);

    SubscriptionDto findById (int id);

    List<SubscriptionDto> findAll();

    void addTariff(int subId, int tariffId) throws SubscriptionException;

    List<Tariff> getAllByService(int id);
}
