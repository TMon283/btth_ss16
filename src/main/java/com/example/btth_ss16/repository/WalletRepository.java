package com.example.btth_ss16.repository;

import com.example.btth_ss16.model.TransactionHistory;
import com.example.btth_ss16.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {
}
