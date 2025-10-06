package com.kennet.Expense.Manager.exception;

/**
 * Exception thrown when an entity is not found in persistence.
 */
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
