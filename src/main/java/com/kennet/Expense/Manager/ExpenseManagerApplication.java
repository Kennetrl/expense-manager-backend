package com.kennet.Expense.Manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class ExpenseManagerApplication {

	public static void main(String[] args) {
		// Cargar las variables del .env
		Dotenv dotenv = Dotenv.load();

		// Setearlas como propiedades del sistema para que Spring Boot las lea
		System.setProperty("DB_USERNAME", dotenv.get("DB_USERNAME"));
		System.setProperty("DB_PASSWORD", dotenv.get("DB_PASSWORD"));
		System.setProperty("JWT_SECRET", dotenv.get("JWT_SECRET"));
		System.setProperty("JWT_EXPIRATION", dotenv.get("JWT_EXPIRATION"));

		SpringApplication.run(ExpenseManagerApplication.class, args);
	}
}