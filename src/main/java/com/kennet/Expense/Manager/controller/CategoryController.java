package com.kennet.Expense.Manager.controller;

import com.kennet.Expense.Manager.model.Category;
import com.kennet.Expense.Manager.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
/**
 * REST endpoints for managing categories (income/expense types).
 */
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    /**
     * Lists all categories.
     */
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    /**
     * Retrieves a category by id.
     */
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        return categoryService.getCategoryById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    /**
     * Creates a new category.
     */
    public Category createCategory(@RequestBody Category category) {
        return categoryService.saveCategory(category);
    }

    @PutMapping("/{id}")
    /**
     * Updates an existing category.
     */
    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody Category categoryDetails) {
        return categoryService.getCategoryById(id).map(category -> {
            category.setName(categoryDetails.getName());
            category.setType(categoryDetails.getType());
            category.setUser(categoryDetails.getUser());
            return ResponseEntity.ok(categoryService.saveCategory(category));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    /**
     * Deletes a category by id.
     */
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        if(categoryService.getCategoryById(id).isPresent()) {
            categoryService.deleteCategory(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
