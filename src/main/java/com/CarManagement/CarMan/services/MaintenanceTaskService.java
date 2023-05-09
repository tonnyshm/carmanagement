package com.CarManagement.CarMan.services;

import com.CarManagement.CarMan.model.Expense;
import com.CarManagement.CarMan.model.MaintenanceTask;
import com.CarManagement.CarMan.model.User;
import com.CarManagement.CarMan.repository.MaintenanceTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MaintenanceTaskService {

    @Autowired
    private MaintenanceTaskRepository maintenanceTaskRepository;

    @Autowired
    private UsersDetailsServicesImpl userService;

    public MaintenanceTask saveMaintenanceTask(MaintenanceTask maintenanceTask) {
        // Get the current user from the UserService
        User currentUser = userService.getCurrentUser();

        // Set the current user as the owner of the maintenance task
        maintenanceTask.setUser(currentUser);

        // Save the maintenance task to the database
        return maintenanceTaskRepository.save(maintenanceTask);
    }

    public List<MaintenanceTask> findIncompleteMaintenanceTasksByUser(User user) {
        return maintenanceTaskRepository.findIncompleteMaintenanceTasksByUser(user);
    }

    public Optional<MaintenanceTask> findById(Long id) {
        return maintenanceTaskRepository.findById(id);
    }

    public List<MaintenanceTask> findByUser(User user) {
        return maintenanceTaskRepository.findByUser(user);
    }

    public List<MaintenanceTask> findByUsername(String username) {
        return maintenanceTaskRepository.findByUser_Username(username);
    }

    public List<MaintenanceTask> findMaintenanceByUserAndCompleted(User user, boolean bool) {
        return maintenanceTaskRepository.findByUserAndCompleted(user, bool);
    }

}
