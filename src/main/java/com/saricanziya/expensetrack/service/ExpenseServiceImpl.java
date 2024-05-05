package com.saricanziya.expensetrack.service;

import com.saricanziya.expensetrack.entity.Expense;
import com.saricanziya.expensetrack.exception.ResourceNotFoundException;
import com.saricanziya.expensetrack.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ExpenseServiceImpl implements ExpenseService{

    private ExpenseRepository expenseRepository;

    @Autowired
    public ExpenseServiceImpl(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    @Override
    public Page<Expense> getAllExpenses(Pageable page) {
        return expenseRepository.findAll(page);
    }

    @Override
    public Expense getExpenseById(Long id) {
        Optional<Expense> expense = expenseRepository.findById(id);
        if(expense.isPresent())
            return expense.get();
        throw new ResourceNotFoundException("Expense is not found for id: " + id);
    }

    @Override
    public void deleteExpenseById(Long id) {
        Expense expenseById = getExpenseById(id);
        expenseRepository.delete(expenseById);
    }

    @Override
    public Expense saveExpense(Expense expense) {
        return expenseRepository.save(expense);
    }

    @Override
    public Expense updateExpense(Long id, Expense expense) {
        Expense existingExpense = getExpenseById(id);

        existingExpense.setName(expense.getName() != null ? expense.getName() : existingExpense.getName());
        existingExpense.setCategory(expense.getCategory() != null ? expense.getCategory() : existingExpense.getCategory());
        existingExpense.setAmount(expense.getAmount() != null ? expense.getAmount() : existingExpense.getAmount());
        existingExpense.setDescription(expense.getDescription() != null ? expense.getDescription() : existingExpense.getDescription());
        existingExpense.setDate(expense.getDate() != null ? expense.getDate() : existingExpense.getDate());

        return expenseRepository.save(existingExpense);
    }

    @Override
    public List<Expense> readByCategory(String category, Pageable page) {
        List<Expense> expenseList = expenseRepository.findByCategory(category, page).toList();

        if(!expenseList.isEmpty())
            return expenseList;
        throw new ResourceNotFoundException("Expense is not found for category: " + category);
    }

    @Override
    public List<Expense> readByName(String name, Pageable page) {
        List<Expense> expenseList = expenseRepository.findByNameContaining(name, page).toList();

        if(!expenseList.isEmpty())
            return expenseList;
        throw new ResourceNotFoundException("Expense is not found for name: " + name);

    }

    @Override
    public List<Expense> readByBetweenDate(LocalDate startDate, LocalDate endDate, Pageable page) {

        if(startDate == null){
            startDate = LocalDate.of(2000, 1, 1);
        }
        if(endDate == null){
            endDate = LocalDate.now();
        }
        List<Expense> expenseList = expenseRepository.findByDateBetween(startDate, endDate, page).toList();

        if(!expenseList.isEmpty())
            return expenseList;
        throw new ResourceNotFoundException("Expense is not found for between dates: " + startDate + " - " + endDate);
    }

    @Override
    public List<Expense> getExpensesAboveAmount(BigDecimal amount, Pageable page) {
        List<Expense> expenseList = expenseRepository.findByAmountGreaterThanEqual(amount, page).toList();

        if(!expenseList.isEmpty())
            return expenseList;
        throw new ResourceNotFoundException("Expense is not found for above amount: " + amount);
    }


}
