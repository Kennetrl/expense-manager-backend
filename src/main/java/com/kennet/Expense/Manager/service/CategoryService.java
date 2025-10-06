package com.kennet.Expense.Manager.service;

import com.kennet.Expense.Manager.exception.ResourceNotFoundException;
import com.kennet.Expense.Manager.model.Category;
import com.kennet.Expense.Manager.model.User;
import com.kennet.Expense.Manager.repository.CategoryRepository;
import com.kennet.Expense.Manager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
/**
 * Business logic for categories.
 * <p>
 * Ensures referenced user exists and provides CRUD helpers.
 */
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ValidationService validationService;

    // Crear o actualizar categoría
    /**
     * Creates or updates a category after validating the owning user.
     */
    public Category saveCategory(Category category) {
        User user = validationService.validateExistence(category.getUser().getId(), userRepository, "Usuario");
        category.setUser(user);
        return categoryRepository.save(category);
    }

    // Obtener todas las categorías
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    // Obtener categoría por ID
    public Optional<Category> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    // Eliminar categoría
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    // Listar categorías de un usuario
    public List<Category> getCategoriesByUserId(Long userId) {
        return categoryRepository.findByUserId(userId);
    }
}
