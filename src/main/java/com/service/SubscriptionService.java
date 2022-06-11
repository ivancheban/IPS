package com.service;

import com.dto.SubscriptionDto;

import com.exceptions.SubscriptionException;


import com.model.Subscription;


import java.util.List;

public interface SubscriptionService {

    boolean create(SubscriptionDto subscriptionDto) throws SubscriptionException;

    SubscriptionDto update(SubscriptionDto subscriptionDto);

    boolean delete(int id);

    SubscriptionDto findByName(String name);

    List<Subscription> findAll();
}
