package com.CarManagement.CarMan.services;

import com.CarManagement.CarMan.model.Car;
import com.CarManagement.CarMan.model.Expense;
import com.CarManagement.CarMan.model.User;
import com.CarManagement.CarMan.repository.CarRepository;
import com.CarManagement.CarMan.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {


    @Autowired
    UserRepository userRepository;
    @Autowired
    private CarRepository carRepository;

    @Autowired
    private UsersDetailsServicesImpl userService;

    public Car save(Car car, User user) {
        car.setUser(user);
        return carRepository.save(car);
    }

    public Car save(Car car) {
        // Get the current user from the UserService
        User currentUser = userService.getCurrentUser();

        // Set the current user as the owner of the maintenance task
        car.setUser(currentUser);

        // Save the maintenance task to the database
        return carRepository.save(car);
    }

    public List<Car> findByUser(User user) {
        return carRepository.findByUser(user);
    }

    public List<Car> searchCars(String searchTerm) {
        return carRepository.findByMakeContainingIgnoreCaseOrModelContainingIgnoreCase(searchTerm, searchTerm);
    }

    public List<Car> findAllCars() {
        return carRepository.findAll();
    }


    public List<Car> findAll() {
        return carRepository.findAll();
    }

    public void deleteById(Long id) {
        carRepository.deleteById(id);
    }

    public Car findById(Long id) {
        return carRepository.findById(id).orElse(null);
    }


    public List<Car> getCarsForCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = null;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }

        // Assuming you have a method in CarRepository to find cars by the user's username
        return carRepository.findByUser_Username(username);
    }

    @Cacheable(cacheNames = "cars", key = "#username")
    public List<Car> findByUsername(String username) {
        return carRepository.findByUser_Username(username);
    }

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public Car getCarById(Long id) {
        return carRepository.findById(id).orElse(null);
    }

    public List<Car> getCarsByUser(User user) {
        return carRepository.findByUser(user);
    }

}

