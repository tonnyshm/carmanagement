package com.CarManagement.CarMan.repository;

import com.CarManagement.CarMan.model.Car;
import com.CarManagement.CarMan.model.Expense;
import com.CarManagement.CarMan.model.FuelUsage;
import com.CarManagement.CarMan.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FuelUsageRepository extends JpaRepository<FuelUsage, Long> {
    List<FuelUsage> findByUser(User user);
    List<FuelUsage> findByUser_Username(String username);

    List<FuelUsage> findByUserAndCarModelContainingIgnoreCase(User user, String model);
    List<FuelUsage> findByCar_ModelContainingIgnoreCase(String model);
}
