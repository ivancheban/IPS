package com.service;

import com.dao.SubscriptionDao;
import com.dto.SubscriptionDto;
import com.exceptions.SubscriptionException;
import com.mapper.BusinessMapper;
import com.model.Subscription;

import java.util.List;

public class SubscriptionServiceImpl implements SubscriptionService{


    SubscriptionDao subscriptionDao = new SubscriptionDao();
    private BusinessMapper businessMapper;

    @Override
    public boolean create(SubscriptionDto subscriptionDto) throws SubscriptionException {

        Subscription subscription = BusinessMapper.convertSubscription(subscriptionDto);
        subscriptionDao.create(subscription);

        if ((subscription.getId() == 0 )){
            throw  new SubscriptionException("subscription is not create");
        }


        return true;
    }

    @Override
    public SubscriptionDto update(SubscriptionDto subscriptionDto) {

        Subscription subscription = subscriptionDao.update(businessMapper.getSubscription(subscriptionDto));
        return businessMapper.getSubscriptionDto(subscription);
    }

    @Override
    public boolean delete(int id) {
        return subscriptionDao.delete(id);
    }

    @Override
    public SubscriptionDto findByName(String name) {
        Subscription subscription = subscriptionDao.findByField(name);

        return businessMapper.getSubscriptionDto(subscription);
    }

    @Override
    public List<Subscription> findAll() {

        return subscriptionDao.findAll();
    }
}
