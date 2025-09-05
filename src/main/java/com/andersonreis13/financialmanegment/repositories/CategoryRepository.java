package com.andersonreis13.financialmanegment.repositories;

import com.andersonreis13.financialmanegment.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
