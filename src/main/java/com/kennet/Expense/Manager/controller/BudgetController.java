package com.kennet.Expense.Manager.controller;

import com.kennet.Expense.Manager.model.Budget;
import com.kennet.Expense.Manager.service.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/budgets")
public class BudgetController {

    @Autowired
    private BudgetService budgetService;

    @GetMapping
    public List<Budget> getAllBudgets() {
        return budgetService.getAllBudgets();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Budget> getBudgetById(@PathVariable Long id) {
        return budgetService.getBudgetById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Budget createBudget(@RequestBody Budget budget) {
        return budgetService.saveBudget(budget);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Budget> updateBudget(@PathVariable Long id, @RequestBody Budget budgetDetails) {
        return budgetService.getBudgetById(id).map(budget -> {
            budget.setAmountLimit(budgetDetails.getAmountLimit());
            budget.setPeriod(budgetDetails.getPeriod());
            budget.setStartDate(budgetDetails.getStartDate());
            budget.setEndDate(budgetDetails.getEndDate());
            budget.setUser(budgetDetails.getUser());
            budget.setCategory(budgetDetails.getCategory());
            return ResponseEntity.ok(budgetService.saveBudget(budget));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBudget(@PathVariable Long id) {
        if(budgetService.getBudgetById(id).isPresent()) {
            budgetService.deleteBudget(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
