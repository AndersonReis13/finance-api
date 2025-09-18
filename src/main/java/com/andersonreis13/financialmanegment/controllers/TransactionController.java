package com.andersonreis13.financialmanegment.controllers;

import com.andersonreis13.financialmanegment.dtos.transaction.TransactionCreateRequest;
import com.andersonreis13.financialmanegment.dtos.transaction.TransactionCreateResponse;
import com.andersonreis13.financialmanegment.dtos.transaction.TransactionDTO;
import com.andersonreis13.financialmanegment.services.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("transaction")
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<TransactionCreateResponse> beginTransaction(@RequestBody TransactionCreateRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(transactionService.beginTransaction(request));
    }

    @GetMapping
    public ResponseEntity<List<TransactionDTO>> findAll(){
        return ResponseEntity.ok().body(transactionService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionDTO> findById(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(transactionService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeTransaction(@PathVariable("id") Long id){
        transactionService.removeTransaction(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

}
