package com.example.btth_ss16.service.impl;

import com.example.btth_ss16.model.TransactionHistory;
import com.example.btth_ss16.model.Wallet;
import com.example.btth_ss16.repository.TransactionHistoryRepository;
import com.example.btth_ss16.repository.WalletRepository;
import com.example.btth_ss16.service.IWalletService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class WalletServiceImpl implements IWalletService {

    private final WalletRepository walletRepository;
    private final TransactionHistoryRepository transactionHistoryRepository;

    public WalletServiceImpl(WalletRepository walletRepository,
                             TransactionHistoryRepository transactionHistoryRepository) {
        this.walletRepository = walletRepository;
        this.transactionHistoryRepository = transactionHistoryRepository;
    }

    @Override
    @Transactional
    public void transferMoney(Long fromWalletId, Long toWalletId, BigDecimal amount) {
        Wallet fromWallet = walletRepository.findById(fromWalletId).orElseThrow();
        Wallet toWallet = walletRepository.findById(toWalletId).orElseThrow();

        fromWallet.setBalance(fromWallet.getBalance() - amount.doubleValue());
        walletRepository.save(fromWallet);

        if (true) {
            throw new RuntimeException("Lỗi khi giao dịch!");
        }

        toWallet.setBalance(toWallet.getBalance() + amount.doubleValue());
        walletRepository.save(toWallet);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveSystemLog(String message) {
        System.out.println("Log: " + message);
    }

    @Override
    public List<TransactionHistory> findAll() {
        return transactionHistoryRepository.findAll();
    }

}
