package com.saricanziya.expensetrack.service;

import com.saricanziya.expensetrack.entity.Expense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


public interface ExpenseService {
    Page<Expense> getAllExpenses(Pageable page);

    Expense getExpenseById(Long id);

    void deleteExpenseById(Long id);

    Expense saveExpense(Expense expense);

    Expense updateExpense(Long id, Expense expense);

    List<Expense> readByCategory(String category, Pageable page);

    List<Expense> readByName(String name, Pageable page);

    List<Expense> readByBetweenDate(LocalDate startDate, LocalDate endDate, Pageable page);

    List<Expense> getExpensesAboveAmount(BigDecimal amount, Pageable page);

}
