package com.kennet.Expense.Manager.service;

import com.kennet.Expense.Manager.model.Category;
import com.kennet.Expense.Manager.model.Expense;
import com.kennet.Expense.Manager.model.User;
import com.kennet.Expense.Manager.repository.CategoryRepository;
import com.kennet.Expense.Manager.repository.ExpenseRepository;
import com.kennet.Expense.Manager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ValidationService validationService;

    public Expense saveExpense(Expense expense) {
        User user = validationService.validateExistence(expense.getUser().getId(), userRepository, "Usuario");
        Category category = validationService.validateExistence(expense.getCategory().getId(), categoryRepository, "Categoría");
        expense.setUser(user);
        expense.setCategory(category);
        return expenseRepository.save(expense);
    }

    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    public Optional<Expense> getExpenseById(Long id) {
        return expenseRepository.findById(id);
    }

    public void deleteExpense(Long id) {
        expenseRepository.deleteById(id);
    }

    public List<Expense> getExpensesByUserId(Long userId) {
        return expenseRepository.findByUserId(userId);
    }
}
