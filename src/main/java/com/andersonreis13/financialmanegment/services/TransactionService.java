package com.andersonreis13.financialmanegment.services;

import com.andersonreis13.financialmanegment.dtos.account.AccountDTO;
import com.andersonreis13.financialmanegment.dtos.category.CategoryDTO;
import com.andersonreis13.financialmanegment.dtos.transaction.*;
import com.andersonreis13.financialmanegment.entities.Account;
import com.andersonreis13.financialmanegment.entities.Category;
import com.andersonreis13.financialmanegment.entities.Transaction;
import com.andersonreis13.financialmanegment.entities.User;
import com.andersonreis13.financialmanegment.entities.enums.TransactionType;
import com.andersonreis13.financialmanegment.exceptions.BadRequestException;
import com.andersonreis13.financialmanegment.exceptions.NotFoundException;
import com.andersonreis13.financialmanegment.mapper.transction.TransactionMapper;
import com.andersonreis13.financialmanegment.repositories.AccountRepository;
import com.andersonreis13.financialmanegment.repositories.CategoryRepository;
import com.andersonreis13.financialmanegment.repositories.TransactionRepository;
import com.andersonreis13.financialmanegment.repositories.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final CategoryRepository categoryRepository;
    private final AccountRepository accountRepository;
    private final UserRepository  userRepository;

    public TransactionService(TransactionRepository transactionRepository, CategoryRepository categoryRepository, AccountRepository accountRepository, UserRepository userRepository) {
        this.transactionRepository = transactionRepository;
        this.categoryRepository = categoryRepository;
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
    }

    public List<TransactionDTO> findAll() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        User user = userRepository.findUserByEmail(email)
                .orElseThrow(()-> new NotFoundException("Usuario não encontrado"));

        List<Transaction> transactions = transactionRepository.findAllByUserId(user.getId());

        return transactions.stream()
                .map(transaction -> new TransactionDTO(transaction.getId(),
                        transaction.getAccountId().getId(), transaction.getCategoryId().getId(),
                        transaction.getTransactionType(), transaction.getDescription(),
                        transaction.getTransactionDate()))
                .collect(Collectors.toList());
    }

    public TransactionDTO findById(Long id) {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Transação não encontrada"));

        return new TransactionDTO(transaction.getId(),
                transaction.getAccountId().getId(), transaction.getCategoryId().getId(),
                transaction.getTransactionType(), transaction.getDescription(),
                transaction.getTransactionDate());
    }

    public TransactionCreateResponse beginTransaction(TransactionCreateRequest request){
        Account account = accountRepository.findById(request.accountId())
                .orElseThrow(()-> new NotFoundException("Conta não encontrada"));
        Category category = categoryRepository.findById(request.accountId())
                .orElseThrow(()-> new NotFoundException("Categoria não encontrada"));

        if(request.amount().compareTo(BigDecimal.ZERO) < 0){
            throw new BadRequestException("O valor da transferencia não pode ser menor que 0");
        }

        if(request.transactionType().equals(TransactionType.RECEITA)){
            BigDecimal previousValue = account.getBalance();
            account.setBalance(previousValue.add(request.amount()));
        }else if (request.transactionType().equals(TransactionType.DESPESA)){
            BigDecimal previousValue = account.getBalance();
            account.setBalance(previousValue.subtract(request.amount()));

        }else{
            throw new BadRequestException("o tipo de transação deve ser RECEITA ou DESPESA");
        }

        Transaction transaction = transactionRepository.save(TransactionMapper.dtoToEntity(request, account, category));

        return new TransactionCreateResponse(transaction.getId(),
                new AccountDTO(account.getId(),account.getUserId().getId(),
                        account.getName(), account.getAccountType(), account.getBalance()),
                new CategoryDTO(category.getId(), category.getUserId().getId(), category.getName()),
                transaction.getTransactionType(), transaction.getDescription(), transaction.getTransactionDate());
    }


    public void removeTransaction(Long id){
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Transação não encontrada"));

        transactionRepository.delete(transaction);
    }

}