package com.andersonreis13.financialmanegment.repositories;

import com.andersonreis13.financialmanegment.entities.Budget;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BudgetRepository extends JpaRepository<Budget, Long> {
}
