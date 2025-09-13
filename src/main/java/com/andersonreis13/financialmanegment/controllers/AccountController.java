package com.andersonreis13.financialmanegment.controllers;

import com.andersonreis13.financialmanegment.dtos.account.*;
import com.andersonreis13.financialmanegment.services.AccountService;
import org.apache.juli.logging.Log;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping(value = "account")
public class AccountController {

    private final AccountService accountService;

    private static final Logger logger = Logger.getLogger(AccountController.class.getName());

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public ResponseEntity<List<AccountDTO>> findAll(){
        return ResponseEntity.ok().body(accountService.findAll());
    }

    @PostMapping
    public ResponseEntity<AccountCreateResponse> createAccount(@RequestBody AccountCreateRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(accountService.createAccount(request));
    }

    @PutMapping("/update")
    public ResponseEntity<AccountUpdateResponse> updateAccount(@RequestBody AccountUpdateRequest request){
        return ResponseEntity.ok().body(accountService.updateAccount(request));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteAccount(@PathVariable(value = "id")Long id){
        accountService.deleteAccount(id);
        return ResponseEntity.ok().body(null);
    }
}
