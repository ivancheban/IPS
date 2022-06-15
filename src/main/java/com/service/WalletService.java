package com.service;


import com.dto.WalletDto;

import com.exceptions.WalletException;

import com.model.Wallet;

import java.util.List;

public interface WalletService {

    boolean create(WalletDto walletDto) throws WalletException;

    WalletDto update(WalletDto walletDto);

    boolean delete(int id);

    WalletDto findByNumber(String number);

    List<Wallet> findAll();
}
