package com.kennet.Expense.Manager.service;

import com.kennet.Expense.Manager.model.Budget;
import com.kennet.Expense.Manager.model.Category;
import com.kennet.Expense.Manager.model.User;
import com.kennet.Expense.Manager.repository.BudgetRepository;
import com.kennet.Expense.Manager.repository.CategoryRepository;
import com.kennet.Expense.Manager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
/**
 * Business logic for budgets.
 * <p>
 * Validates related entities and delegates persistence to the repository.
 */
public class BudgetService {

    @Autowired
    private BudgetRepository budgetRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ValidationService validationService;

    /**
     * Creates or updates a budget after validating related user and category.
     */
    public Budget saveBudget(Budget budget) {
        User user = validationService.validateExistence(budget.getUser().getId(), userRepository, "Usuario");
        Category category = validationService.validateExistence(budget.getCategory().getId(), categoryRepository, "Categoría");
        budget.setUser(user);
        budget.setCategory(category);
        return budgetRepository.save(budget);
    }

    public List<Budget> getAllBudgets() {
        return budgetRepository.findAll();
    }

    public Optional<Budget> getBudgetById(Long id) {
        return budgetRepository.findById(id);
    }

    public void deleteBudget(Long id) {
        budgetRepository.deleteById(id);
    }
}