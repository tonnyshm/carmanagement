package com.CarManagement.CarMan.repository;

import com.CarManagement.CarMan.model.Car;
import com.CarManagement.CarMan.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long>{
    List<Car> findByUser(User user);
    List<Car> findByUser_Username(String username);
    List<Car> findAll();

    List<Car> findByUserAndModelContainingIgnoreCase(User user, String model);
    List<Car> findByMakeContainingIgnoreCaseOrModelContainingIgnoreCase(String make, String model);
}
