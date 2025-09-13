package com.andersonreis13.financialmanegment.dtos.account;

import java.math.BigDecimal;

public record AccountCreateRequest(Long id,
                                   Long userId,
                                   String name,
                                   String accountType,
                                   BigDecimal balance) {
}
