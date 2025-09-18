package com.andersonreis13.financialmanegment.dtos.transaction;

import com.andersonreis13.financialmanegment.dtos.account.AccountDTO;
import com.andersonreis13.financialmanegment.dtos.category.CategoryDTO;
import com.andersonreis13.financialmanegment.entities.enums.TransactionType;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record TransactionCreateResponse(Long id,
                                        AccountDTO account,
                                        CategoryDTO category,
                                        TransactionType transactionType,
                                        String description,
                                        LocalDateTime startTransaction) {
}
