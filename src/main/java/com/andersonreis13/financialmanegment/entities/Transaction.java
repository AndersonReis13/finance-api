package com.andersonreis13.financialmanegment.entities;

import com.andersonreis13.financialmanegment.dtos.account.AccountCreateRequest;
import com.andersonreis13.financialmanegment.entities.enums.TransactionType;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity()
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account accountId;

    @ManyToOne()
    @JoinColumn(name = "category_id", nullable = false)
    private Category categoryId;

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_type")
    private TransactionType transactionType;

    private String description;

    @Column(name = "transaction_date")
    private LocalDateTime transactionDate;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public Transaction(Long id, Account accountId, Category categoryId, TransactionType transactionType, String description, LocalDateTime transactionDate, LocalDateTime createdAt) {
        this.id = id;
        this.accountId = accountId;
        this.categoryId = categoryId;
        this.transactionType = transactionType;
        this.description = description;
        this.transactionDate = transactionDate;
        this.createdAt = createdAt;
    }

    public Transaction() {
    }

    public Long getId() {
        return id;
    }

    public Account getAccountId() {
        return accountId;
    }

    public void setAccountId(Account accountId) {
        this.accountId = accountId;
    }

    public Category getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Category categoryId) {
        this.categoryId = categoryId;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
