package com.kennet.Expense.Manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.github.cdimascio.dotenv.Dotenv;

/**
 * Application entry point for the Expense Manager backend.
 * <p>
 * Responsibilities:
 * - Loads environment variables from a local .env file (DB and JWT config).
 * - Exposes them as System properties so Spring can bind them via application.properties.
 * - Boots the Spring application context.
 */
@SpringBootApplication
public class ExpenseManagerApplication {

	public static void main(String[] args) {
		// Load variables from .env so Spring can read them as System properties
		Dotenv dotenv = Dotenv.load();

		// Expose variables as System properties consumed by Spring configuration
		System.setProperty("DB_USERNAME", dotenv.get("DB_USERNAME"));
		System.setProperty("DB_PASSWORD", dotenv.get("DB_PASSWORD"));
		System.setProperty("JWT_SECRET", dotenv.get("JWT_SECRET"));
		System.setProperty("JWT_EXPIRATION", dotenv.get("JWT_EXPIRATION"));

		SpringApplication.run(ExpenseManagerApplication.class, args);
	}
}