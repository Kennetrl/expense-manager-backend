package com.kennet.Expense.Manager.service;

import com.kennet.Expense.Manager.exception.ResourceNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class ValidationService {

    public <T> T validateExistence(Long id, JpaRepository<T, Long> repo, String entityName) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(entityName + " no encontrado con id: " + id));
    }
}
