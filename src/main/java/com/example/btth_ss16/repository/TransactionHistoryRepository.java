package com.example.btth_ss16.repository;

import com.example.btth_ss16.model.TransactionHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionHistoryRepository extends JpaRepository<TransactionHistory, Long> {

    Page<TransactionHistory> findByWallet_Id(Long walletId, Pageable pageable);

    @Query("SELECT t FROM TransactionHistory t WHERE t.transactionAmount > :amount")
    List<TransactionHistory> findTransactionsGreaterThan(Double amount);
}
