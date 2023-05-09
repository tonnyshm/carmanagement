package com.CarManagement.CarMan.repository;

import com.CarManagement.CarMan.model.Expense;
import com.CarManagement.CarMan.model.MaintenanceTask;
import com.CarManagement.CarMan.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MaintenanceTaskRepository extends JpaRepository<MaintenanceTask, Long> {

    List<MaintenanceTask> findByUser(User user);
    List<MaintenanceTask> findByUser_Username(String username);

    List<MaintenanceTask> findIncompleteMaintenanceTasksByUser(User user);
    List<MaintenanceTask> findByUserAndCompleted(User user, boolean bool);

}
