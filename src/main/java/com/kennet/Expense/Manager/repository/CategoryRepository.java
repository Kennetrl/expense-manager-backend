package com.kennet.Expense.Manager.repository;

import com.kennet.Expense.Manager.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository <Category, Long> {
    List<Category> findByUserId(Long userId);
}
