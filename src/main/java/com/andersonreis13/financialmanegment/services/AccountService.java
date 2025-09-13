package com.andersonreis13.financialmanegment.services;

import com.andersonreis13.financialmanegment.dtos.account.*;
import com.andersonreis13.financialmanegment.dtos.user.UserDTO;
import com.andersonreis13.financialmanegment.entities.Account;
import com.andersonreis13.financialmanegment.entities.User;
import com.andersonreis13.financialmanegment.entities.enums.AccountType;
import com.andersonreis13.financialmanegment.exceptions.BadRequestException;
import com.andersonreis13.financialmanegment.exceptions.NotFoundException;
import com.andersonreis13.financialmanegment.mapper.account.AccountMapper;
import com.andersonreis13.financialmanegment.repositories.AccountRepository;
import com.andersonreis13.financialmanegment.repositories.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountService {
    private final UserRepository userRepository;
    private final AccountRepository accountRepository;

    public AccountService(UserRepository userRepository, AccountRepository accountRepository) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
    }

    public List<AccountDTO> findAll(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        User user = userRepository.findUserByEmail(email)
                .orElseThrow(()-> new NotFoundException("Usuario não encontrado"));

        List<Account> accounts = accountRepository.findByUserId(user.getId());

        return accounts.stream()
                .map(account -> new AccountDTO(account.getId(), account.getUserId().getId(),
                        account.getName(), account.getAccountType(), account.getBalance())
                ).collect(Collectors.toList());
    }

    public AccountDTO findById(Long id){
        Account account = accountRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Conta não encontrada"));
        return new AccountDTO(account.getId(), account.getUserId().getId(),
                account.getName(), account.getAccountType(), account.getBalance());
    }

    public AccountCreateResponse createAccount(AccountCreateRequest request){
        User user = userRepository.findById(request.userId())
                .orElseThrow(()-> new NotFoundException("Usuario não exite"));

        accountRepository.save(AccountMapper.CreateDtoToEntity(request, user));

        if(request.balance().compareTo(BigDecimal.ZERO) < 0){
           throw  new BadRequestException("O valor da conta não pode ser menor que 0");
        }

        Account account = accountRepository.save(AccountMapper.CreateDtoToEntity(request, user));

        return new AccountCreateResponse("Criado com sucesso",
                new UserDTO(user.getId(), user.getName(), user.getEmail()),
                account.getId(),
                request.name(),
                request.accountType(),
                request.balance());
    }

    public AccountUpdateResponse updateAccount(AccountUpdateRequest request){
        Account account = accountRepository.findById(request.id())
                .orElseThrow(()-> new NotFoundException("Conta não encontrada"));

        if(request.balance().compareTo(BigDecimal.ZERO) < 0){
            throw  new BadRequestException("O valor da conta não pode ser menor que 0");
        }

        account.setName(request.name());
        account.setAccountType(AccountType.valueOf(request.account()));
        account.setBalance(request.balance());
        account.setUpdatedAt(LocalDateTime.now());

        accountRepository.save(account);

        return new AccountUpdateResponse("Conta atualizada",
                new AccountDTO(account.getId(), account.getUserId().getId(),
                        account.getName(),
                        account.getAccountType(),
                        account.getBalance()));
    }

    public void deleteAccount(Long id){
        Account account = accountRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Conta não encontrada"));

        accountRepository.delete(account);
    }


}
