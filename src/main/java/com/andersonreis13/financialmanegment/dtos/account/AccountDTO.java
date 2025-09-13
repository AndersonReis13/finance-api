package com.andersonreis13.financialmanegment.dtos.account;

import com.andersonreis13.financialmanegment.entities.enums.AccountType;

import java.math.BigDecimal;

public record AccountDTO(Long id,
                         Long userId,
                         String name,
                         AccountType AccountType,
                         BigDecimal balance) {
}
