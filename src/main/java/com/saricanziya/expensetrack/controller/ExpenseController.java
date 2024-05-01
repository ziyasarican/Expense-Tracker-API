package com.saricanziya.expensetrack.controller;

import com.saricanziya.expensetrack.entity.Expense;
import com.saricanziya.expensetrack.service.ExpenseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ExpenseController {

    private ExpenseService expenseService;

    @Autowired
    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping("/expenses")
    public List<Expense> getAllExpenses(Pageable page){
        return expenseService.getAllExpenses(page).toList();
    }

    @GetMapping("/expense/{id}")
    public Expense getExpenseById(@PathVariable Long id){
        return expenseService.getExpenseById(id);
    }

    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @DeleteMapping("/expense")
    public void deleteExpenseById(@RequestParam Long id){
        expenseService.deleteExpenseById(id);
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping("/expense")
    public Expense saveExpense(@Valid @RequestBody Expense expense){
        return expenseService.saveExpense(expense);
    }

    @PostMapping("/expense/{id}")
    public Expense updateExpense(@RequestBody Expense expense, @PathVariable Long id){
        return expenseService.updateExpense(id, expense);
    }

}
