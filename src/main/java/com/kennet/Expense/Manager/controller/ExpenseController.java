package com.kennet.Expense.Manager.controller;

import com.kennet.Expense.Manager.model.Expense;
import com.kennet.Expense.Manager.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @GetMapping
    public List<Expense> getAllExpenses() {
        return expenseService.getAllExpenses();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Expense> getExpenseById(@PathVariable Long id) {
        return expenseService.getExpenseById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Expense createExpense(@RequestBody Expense expense) {
        return expenseService.saveExpense(expense);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Expense> updateExpense(@PathVariable Long id, @RequestBody Expense expenseDetails) {
        return expenseService.getExpenseById(id).map(expense -> {
            expense.setAmount(expenseDetails.getAmount());
            expense.setDate(expenseDetails.getDate());
            expense.setDescription(expenseDetails.getDescription());
            expense.setUser(expenseDetails.getUser());
            expense.setCategory(expenseDetails.getCategory());
            return ResponseEntity.ok(expenseService.saveExpense(expense));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpense(@PathVariable Long id) {
        if(expenseService.getExpenseById(id).isPresent()) {
            expenseService.deleteExpense(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
