package com.service;

import com.dao.SubscriptionDao;
import com.dto.SubscriptionDto;
import com.exceptions.SubscriptionException;
import com.mapper.BusinessMapper;
import com.model.Subscription;
import com.model.Tariff;
import java.util.List;
import java.util.stream.Collectors;

public class SubscriptionServiceImpl implements SubscriptionService {
    SubscriptionDao subscriptionDao = new SubscriptionDao();

    @Override
    public boolean create(SubscriptionDto subscriptionDto) throws SubscriptionException {
        Subscription subscription = BusinessMapper.convertSubscription(subscriptionDto);
        subscriptionDao.create(subscription);
        if ((subscription.getId() == 0)) {
            throw new SubscriptionException("subscription is not create");
        }
        return true;
    }
    @Override
    public SubscriptionDto update(SubscriptionDto subscriptionDto) {
        Subscription subscription = subscriptionDao.update(BusinessMapper.convertSubscription(subscriptionDto));
        return BusinessMapper.convertSubDto(subscription);
    }
    @Override
    public boolean delete(int id) {
        return subscriptionDao.delete(id);
    }
    @Override
    public SubscriptionDto findByName(String name) {
        Subscription subscription = subscriptionDao.findByField(name);
        return BusinessMapper.getSubscriptionDto(subscription);
    }
    @Override
    public SubscriptionDto findById(int id) {
        Subscription subscription = subscriptionDao.findById(id);
        return BusinessMapper.getSubscriptionDto(subscription);
    }

    @Override
    public List<Tariff> getAllByService(int id) {
        return subscriptionDao.getAllByService(id);
    }

    @Override
    public List<SubscriptionDto> findAll() {
        return subscriptionDao.findAll().stream().map(subscription -> BusinessMapper.convertSubDto(subscription)).collect(Collectors.toList());
    }

    @Override
    public void addTariff(int subId, int tariffId)throws SubscriptionException {
        subscriptionDao.addTariff(subId, tariffId);
    }

    public List<Tariff> getAllTariffs(int sub_id){
        return subscriptionDao.getAllTariffs(sub_id);
    }


}
