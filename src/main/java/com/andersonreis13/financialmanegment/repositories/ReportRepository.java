package com.andersonreis13.financialmanegment.repositories;

import com.andersonreis13.financialmanegment.entities.Reports;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Reports, Long> {
}
