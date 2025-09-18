package com.andersonreis13.financialmanegment.dtos.transaction;

import com.andersonreis13.financialmanegment.entities.enums.TransactionType;

import java.math.BigDecimal;

public record TransactionCreateRequest(Long id,
                                       Long accountId,
                                       Long categoryId,
                                       TransactionType transactionType,
                                       String description,
                                       BigDecimal amount) {
}
