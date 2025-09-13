package com.andersonreis13.financialmanegment.repositories;

import com.andersonreis13.financialmanegment.entities.Account;
import com.andersonreis13.financialmanegment.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {

    @Query(value = "SELECT * FROM accounts a WHERE user_id = :userId ORDER BY created_at DESC",
            nativeQuery = true)
    List<Account> findByUserId(@Param("userId") Long userId);

}
