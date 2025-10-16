package com.kennet.Expense.Manager.controller;

import com.kennet.Expense.Manager.model.Income;
import com.kennet.Expense.Manager.model.User;
import com.kennet.Expense.Manager.service.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/income")
/**
 * REST endpoints for managing incomes.
 */
public class IncomeController {

    @Autowired
    private IncomeService incomeService;

    @GetMapping
    public List<Income> getMyIncome(@AuthenticationPrincipal User currentUser) {
        return incomeService.getIncomeByUserId(currentUser.getId());
    }

    @GetMapping("/all")
    /**
     * Lists all income records.
     */
    public List<Income> getAllIncomes() {
        return incomeService.getAllIncomes();
    }

    @GetMapping("/{id}")
    /**
     * Retrieves an income record by id.
     */
    public ResponseEntity<Income> getIncomeById(@PathVariable Long id) {
        return incomeService.getIncomeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    /**
     * Creates a new income record.
     */
    public Income createIncome(@RequestBody Income income) {
        return incomeService.saveIncome(income);
    }

    @PutMapping("/{id}")
    /**
     * Updates an existing income record.
     */
    public ResponseEntity<Income> updateIncome(@PathVariable Long id, @RequestBody Income incomeDetails) {
        return incomeService.getIncomeById(id).map(income -> {
            income.setAmount(incomeDetails.getAmount());
            income.setDate(incomeDetails.getDate());
            income.setDescription(incomeDetails.getDescription());
            income.setUser(incomeDetails.getUser());
            income.setCategory(incomeDetails.getCategory());
            return ResponseEntity.ok(incomeService.saveIncome(income));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    /**
     * Deletes an income record by id.
     */
    public ResponseEntity<Map<String, String>> deleteIncome(@PathVariable Long id) {
        if(incomeService.getIncomeById(id).isPresent()) {
            incomeService.deleteIncome(id);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Income successfully deleted");
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.notFound().build();
    }
}
