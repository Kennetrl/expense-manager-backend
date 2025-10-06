package com.kennet.Expense.Manager.repository;

import com.kennet.Expense.Manager.model.Income;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository for {@link Income} persistence and queries.
 */
public interface IncomeRepository extends JpaRepository <Income,Long> {
    List<Income> findByUserId(Long userId);
}
