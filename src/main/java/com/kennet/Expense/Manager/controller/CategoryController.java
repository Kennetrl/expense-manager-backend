package com.kennet.Expense.Manager.controller;

import com.kennet.Expense.Manager.model.Category;
import com.kennet.Expense.Manager.model.User;
import com.kennet.Expense.Manager.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public List<Category> getMyCategories(@AuthenticationPrincipal User currentUser) {
        return categoryService.getCategoriesByUserId(currentUser.getId());
    }

    @GetMapping("/all")
    public List<Category> getAllCategories(){
        return categoryService.getAllCategories();
    }


    @PostMapping
    public ResponseEntity<Category> createCategory(
            @RequestBody Category category,
            @AuthenticationPrincipal User currentUser) {

        // Asignar usuario actual
        category.setUser(currentUser);

        Category saved = categoryService.saveCategory(category);
        return ResponseEntity.ok(saved);
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
    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(
            @PathVariable Long id,
            @RequestBody Category categoryDetails,
            @AuthenticationPrincipal User currentUser) {

        return categoryService.getCategoryById(id).map(category -> {
            category.setName(categoryDetails.getName());
            category.setType(categoryDetails.getType());
            category.setUser(currentUser); // asegura que siempre hay un usuario
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
