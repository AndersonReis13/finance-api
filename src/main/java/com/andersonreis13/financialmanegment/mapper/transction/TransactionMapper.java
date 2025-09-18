package com.andersonreis13.financialmanegment.mapper.transction;

import com.andersonreis13.financialmanegment.dtos.transaction.TransactionCreateRequest;
import com.andersonreis13.financialmanegment.entities.Account;
import com.andersonreis13.financialmanegment.entities.Category;
import com.andersonreis13.financialmanegment.entities.Transaction;

import java.time.LocalDateTime;

public class TransactionMapper {

    public static Transaction dtoToEntity(TransactionCreateRequest request, Account account, Category category){
        return new Transaction(request.id(),
                account,
                category,
                request.transactionType(),
                request.description(),
                LocalDateTime.now(),
                LocalDateTime.now());
    }
}
