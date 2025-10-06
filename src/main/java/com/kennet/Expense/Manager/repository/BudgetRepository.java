package com.kennet.Expense.Manager.repository;

import com.kennet.Expense.Manager.model.Budget;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository for {@link Budget} persistence and queries.
 */
public interface BudgetRepository extends JpaRepository <Budget, Long> {
    List<Budget> findByUserId(Long userId);
}
