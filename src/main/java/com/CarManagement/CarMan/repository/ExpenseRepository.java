package com.CarManagement.CarMan.repository;

import com.CarManagement.CarMan.model.Expense;
import com.CarManagement.CarMan.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    Page<Expense> findByUser(User user, Pageable pageable);
    Page<Expense> findByCar_ModelAndUser(String model, User user, Pageable pageable);
    List<Expense> findByUser(User user);
    List<Expense> findByUser_Username(String username);
}
