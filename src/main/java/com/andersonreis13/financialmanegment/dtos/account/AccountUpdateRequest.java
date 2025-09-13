package com.andersonreis13.financialmanegment.dtos.account;

import java.math.BigDecimal;

public record AccountUpdateRequest(Long id,
                                   String name,
                                   String account,
                                   BigDecimal balance){

}
