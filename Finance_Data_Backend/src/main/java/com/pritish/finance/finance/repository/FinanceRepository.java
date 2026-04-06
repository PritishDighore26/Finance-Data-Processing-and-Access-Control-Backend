package com.pritish.finance.finance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pritish.finance.finance.entity.FinanceRecord;

import java.time.LocalDate;
import java.util.List;

public interface FinanceRepository extends JpaRepository<FinanceRecord, Long> {

    List<FinanceRecord> findByType(String type);

    List<FinanceRecord> findByCategory(String category);

    List<FinanceRecord> findByDateBetween(LocalDate start, LocalDate end);
}