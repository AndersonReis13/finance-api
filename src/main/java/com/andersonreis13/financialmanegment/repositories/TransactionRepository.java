package com.andersonreis13.financialmanegment.repositories;

import com.andersonreis13.financialmanegment.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
