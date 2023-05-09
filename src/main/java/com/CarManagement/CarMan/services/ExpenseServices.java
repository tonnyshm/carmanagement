package com.CarManagement.CarMan.services;

import com.CarManagement.CarMan.model.Expense;
import com.CarManagement.CarMan.model.FuelUsage;
import com.CarManagement.CarMan.model.User;
import com.CarManagement.CarMan.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExpenseServices {

    @Autowired
    private UsersDetailsServicesImpl userService;

    @Autowired
    private ExpenseRepository expenseRepository;

    public Expense saveExpense(Expense expense) {
        // Get the current user from the UserService
        User currentUser = userService.getCurrentUser();

        // Set the current user as the owner of the maintenance task
        expense.setUser(currentUser);

        // Save the maintenance task to the database
        return expenseRepository.save(expense);
    }

    public Expense save(Expense expense, User user) {
        expense.setUser(user);
        return expenseRepository.save(expense);
    }

    public List<Expense> findByUser(User user) {
        return expenseRepository.findByUser(user);
    }

    public void delete(Expense expense) {
        expenseRepository.delete(expense);
    }


    public Optional<Expense> findById(Long id) {
        return expenseRepository.findById(id);
    }

    public List<Expense> findAll() {
        return expenseRepository.findAll();
    }

    public List<Expense> findByUsername(String username) {
        return expenseRepository.findByUser_Username(username);
    }

}
