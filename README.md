# Expense Manager Backend

Backend for **Expense Manager**, a personal finance application. Built with **Java Spring Boot**, this backend handles secure authentication, user and category management, and CRUD operations for expenses, incomes, and budgets. It provides the foundation for tracking, managing, and analyzing personal finances.

## Features

- **User Management**: register, update, and delete users.
- **Category Management**: create categories for expenses and income.
- **Expenses & Incomes**: CRUD operations for personal financial transactions.
- **Budgets**: define budgets with optional start and end dates.
- **Validation & Error Handling**: ensures users, categories, and other entities exist before saving.
- **Timestamps**: automatic tracking of `createdAt` and `updatedAt`.
- **Secure Authentication**: handles user authentication and authorization.

## Tech Stack

- **Java 17+**
- **Spring Boot 3+**
- **Spring Data JPA** for database access
- **Hibernate** for ORM
- **PostgreSQL** as the main database
- **Spring Security** for authentication

## Project Structure
com.kennet.expense.manager
- ├─ config
- ├─ controller
- ├─ service
- ├─ repository
- ├─ model
- ├─ dto
- └─ exception

## Getting Started

1. Clone the repository:
   ```bash
   git clone https://github.com/Kennetrl/expense-manager-backend.git
