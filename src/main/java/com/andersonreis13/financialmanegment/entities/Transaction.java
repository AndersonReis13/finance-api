package com.andersonreis13.financialmanegment.entities;

import com.andersonreis13.financialmanegment.entities.enums.TransactionType;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity()
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "user_id", nullable = false)
    private User userId;

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
}
