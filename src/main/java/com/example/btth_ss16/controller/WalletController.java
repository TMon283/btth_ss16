package com.example.btth_ss16.controller;

import com.example.btth_ss16.model.TransactionHistory;
import com.example.btth_ss16.repository.TransactionHistoryRepository;
import com.example.btth_ss16.service.IWalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/wallets")
@RequiredArgsConstructor
public class WalletController {

    private final IWalletService walletService;
    private final TransactionHistoryRepository transactionHistoryRepository;

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute(walletService.findAll());
        return "transactions";
    }

    @PostMapping("/transfer")
    public String transferMoney(@RequestParam Long fromWalletId,
                                @RequestParam Long toWalletId,
                                @RequestParam BigDecimal amount) {
        try {
            walletService.transferMoney(fromWalletId, toWalletId, amount);
            return "Giao dịch thành công!";
        } catch (Exception e) {
            walletService.saveSystemLog("Lỗi giao dịch: " + e.getMessage());
            return "Lỗi giao dịch!";
        }
    }

    @GetMapping("/{walletId}/transactions")
    public Page<TransactionHistory> getTransactions(@PathVariable Long walletId,
                                                    @RequestParam(defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "5") int size) {
        return transactionHistoryRepository.findByWallet_Id(walletId, PageRequest.of(page, size));
    }

    @GetMapping("/transactions/filter")
    public List<TransactionHistory> filterTransactions(@RequestParam Double amount) {
        return transactionHistoryRepository.findTransactionsGreaterThan(amount);
    }
}
