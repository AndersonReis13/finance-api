package com.andersonreis13.financialmanegment.mapper.account;

import com.andersonreis13.financialmanegment.dtos.account.AccountCreateRequest;
import com.andersonreis13.financialmanegment.dtos.account.AccountCreateResponse;
import com.andersonreis13.financialmanegment.entities.Account;
import com.andersonreis13.financialmanegment.entities.User;
import com.andersonreis13.financialmanegment.entities.enums.AccountType;

import java.time.LocalDateTime;

public class AccountMapper {

    public static Account CreateDtoToEntity(AccountCreateRequest request, User user){
        return new Account(
                request.id(),
                user,
                request.name(),
                AccountType.valueOf(request.accountType()),
                request.balance(),
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }
}
