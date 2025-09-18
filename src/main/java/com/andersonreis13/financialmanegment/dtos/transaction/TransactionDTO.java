package com.andersonreis13.financialmanegment.dtos.transaction;

import com.andersonreis13.financialmanegment.entities.enums.TransactionType;

import java.time.LocalDateTime;

public record TransactionDTO(Long id,
                             Long accountId,
                             Long CategoryId,
                             TransactionType TransactionType,
                             String description,
                             LocalDateTime transactionDate) {
}
