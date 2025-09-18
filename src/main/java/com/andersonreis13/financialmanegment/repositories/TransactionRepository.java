package com.andersonreis13.financialmanegment.repositories;

import com.andersonreis13.financialmanegment.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Query(value = "SELECT t.* FROM transactions t " +
            "JOIN accounts a ON t.account_id = a.id " +
            "JOIN users u ON a.user_id = u.id " +
            "WHERE u.id = :userId", nativeQuery = true)
    List<Transaction> findAllByUserId(@Param("userId") Long userId);
}
