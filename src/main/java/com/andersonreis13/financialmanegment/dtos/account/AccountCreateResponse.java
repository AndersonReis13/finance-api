package com.andersonreis13.financialmanegment.dtos.account;

import com.andersonreis13.financialmanegment.dtos.user.UserDTO;

import java.math.BigDecimal;

public record AccountCreateResponse(String msg,
                                    UserDTO user,
                                    Long id,
                                    String name,
                                    String AccountType,
                                    BigDecimal balance) {
}
