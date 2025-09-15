package com.andersonreis13.financialmanegment.repositories;

import com.andersonreis13.financialmanegment.entities.Account;
import com.andersonreis13.financialmanegment.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query(value = "SELECT * FROM categories a WHERE user_id = :userId ORDER BY created_at DESC",
            nativeQuery = true)
    List<Category> findByUserId(@Param("userId") Long userId);
}
