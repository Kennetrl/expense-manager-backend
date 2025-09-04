package com.kennet.Expense.Manager.controller;

import com.kennet.Expense.Manager.model.Income;
import com.kennet.Expense.Manager.service.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/income")
public class IncomeController {

    @Autowired
    private IncomeService incomeService;

    @GetMapping
    public List<Income> getAllIncomes() {
        return incomeService.getAllIncomes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Income> getIncomeById(@PathVariable Long id) {
        return incomeService.getIncomeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Income createIncome(@RequestBody Income income) {
        return incomeService.saveIncome(income);
    }

    @PutMapping("/{id}")
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
    public ResponseEntity<Void> deleteIncome(@PathVariable Long id) {
        if(incomeService.getIncomeById(id).isPresent()) {
            incomeService.deleteIncome(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
