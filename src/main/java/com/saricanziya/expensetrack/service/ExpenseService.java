package com.saricanziya.expensetrack.service;

import com.saricanziya.expensetrack.entity.Expense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ExpenseService {
    Page<Expense> getAllExpenses(Pageable page);

    Expense getExpenseById(Long id);

    void deleteExpenseById(Long id);

    Expense saveExpense(Expense expense);

    Expense updateExpense(Long id, Expense expense);
}
