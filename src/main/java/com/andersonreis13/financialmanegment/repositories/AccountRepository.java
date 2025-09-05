package com.andersonreis13.financialmanegment.repositories;

import com.andersonreis13.financialmanegment.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
