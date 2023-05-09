package com.CarManagement.CarMan.repository;

import com.CarManagement.CarMan.model.Expense;
import com.CarManagement.CarMan.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findByUser(User user);
    List<Expense> findByUser_Username(String username);
}
