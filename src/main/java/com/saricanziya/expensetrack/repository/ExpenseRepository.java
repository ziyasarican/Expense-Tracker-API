package com.saricanziya.expensetrack.repository;

import com.saricanziya.expensetrack.entity.Expense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    Page<Expense> findByCategory(String category, Pageable page);

    Page<Expense> findByNameContaining(String keyword, Pageable page);

    Page<Expense> findByDateBetween(LocalDate startDate, LocalDate endDate, Pageable page);

    Page<Expense> findByAmountGreaterThanEqual(BigDecimal amount, Pageable page);
}
