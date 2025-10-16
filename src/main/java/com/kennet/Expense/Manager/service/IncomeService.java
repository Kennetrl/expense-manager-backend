package com.kennet.Expense.Manager.service;

import com.kennet.Expense.Manager.model.Category;
import com.kennet.Expense.Manager.model.Income;
import com.kennet.Expense.Manager.model.User;
import com.kennet.Expense.Manager.repository.CategoryRepository;
import com.kennet.Expense.Manager.repository.IncomeRepository;
import com.kennet.Expense.Manager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
/**
 * Business logic for incomes.
 * <p>
 * Validates user and category existence before persisting.
 */
public class IncomeService {

    @Autowired
    private IncomeRepository incomeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ValidationService validationService;

    public List<Income> getIncomeByUserId(Long userId) {
        return incomeRepository.findByUserId(userId);
    }

    public Income saveIncome(Income income) {
        User user = validationService.validateExistence(income.getUser().getId(), userRepository, "Usuario");
        Category category = validationService.validateExistence(income.getCategory().getId(), categoryRepository, "Categoría");
        income.setUser(user);
        income.setCategory(category);
        return incomeRepository.save(income);
    }

    public List<Income> getAllIncomes() {
        return incomeRepository.findAll();
    }

    public Optional<Income> getIncomeById(Long id) {
        return incomeRepository.findById(id);
    }

    public void deleteIncome(Long id) {
        incomeRepository.deleteById(id);
    }
}
