package com.example.btth_ss16.service;

import com.example.btth_ss16.model.TransactionHistory;

import java.math.BigDecimal;
import java.util.List;

public interface IWalletService {
    void transferMoney(Long fromWalletId, Long toWalletId, BigDecimal amount);
    void saveSystemLog(String message);

    List<TransactionHistory> findAll();
}
