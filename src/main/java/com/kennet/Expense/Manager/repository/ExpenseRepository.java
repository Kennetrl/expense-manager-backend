package com.kennet.Expense.Manager.repository;

import com.kennet.Expense.Manager.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseRepository extends JpaRepository <Expense, Long> {
    List<Expense> findByUserId(Long userId);
}
