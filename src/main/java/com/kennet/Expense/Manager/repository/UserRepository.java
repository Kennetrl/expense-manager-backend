package com.kennet.Expense.Manager.repository;

import com.kennet.Expense.Manager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository for {@link User} persistence and queries.
 */
public interface UserRepository extends JpaRepository <User, Long> {
    Optional<User> findByEmail(String email);

}
