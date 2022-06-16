package com.service;

import com.dao.WalletDao;
import com.dto.WalletDto;
import com.exceptions.WalletException;
import com.mapper.BusinessMapper;
import com.model.Wallet;

import java.util.List;

public class WalletServiceImpl implements WalletService{

    WalletDao walletDao = new WalletDao();

    @Override
    public boolean create(WalletDto walletDto) throws WalletException {
        Wallet wallet = BusinessMapper.walletConversation(walletDto);
        walletDao.create(wallet);

        if(wallet.getId()==0){
            throw new WalletException("wallet is not created");
        }
        return true;
    }

    @Override
    public WalletDto update(WalletDto walletDto) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public WalletDto findByNumber(String number) {
        return null;
    }

    @Override
    public List<Wallet> findAll() {
        return walletDao.findAll();
    }
}
