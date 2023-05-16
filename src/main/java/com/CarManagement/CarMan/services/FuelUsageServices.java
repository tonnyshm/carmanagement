package com.CarManagement.CarMan.services;

import com.CarManagement.CarMan.model.Expense;
import com.CarManagement.CarMan.model.FuelUsage;
import com.CarManagement.CarMan.model.MaintenanceTask;
import com.CarManagement.CarMan.model.User;
import com.CarManagement.CarMan.repository.FuelUsageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FuelUsageServices {

    @Autowired
    private FuelUsageRepository fuelUsageRepository;


    @Autowired
    private UsersDetailsServicesImpl userService;

    public FuelUsage saveFuelUsage(FuelUsage fuelUsage) {

        double totalPrice = fuelUsage.getFuelVolume() * fuelUsage.getPricePerUnit();
        fuelUsage.setTotalPrice(totalPrice);
        // Get the current user from the UserService
        User currentUser = userService.getCurrentUser();

        // Set the current user as the owner of the maintenance task
        fuelUsage.setUser(currentUser);

        // Save the maintenance task to the database
        return fuelUsageRepository.save(fuelUsage);
    }

    public List<FuelUsage> findByUser(User user) {
        return fuelUsageRepository.findByUser(user);
    }

    public Optional<FuelUsage> findById(Long id) {
        return fuelUsageRepository.findById(id);
    }

    public List<FuelUsage> findAll() {
        return (List<FuelUsage>) fuelUsageRepository.findAll();
    }

    public void delete(FuelUsage fuelUsage) {
        fuelUsageRepository.delete(fuelUsage);
    }

    public List<FuelUsage> findByUsername(String username) {
        return fuelUsageRepository.findByUser_Username(username);
    }

    public List<FuelUsage> searchFuelUsages(String searchTerm) {
        return fuelUsageRepository.findByCar_ModelContainingIgnoreCase(searchTerm);
    }

    @Cacheable("fuelUsages")
    public List<FuelUsage> findAllFuelUsages() {
        return fuelUsageRepository.findAll();
    }


    public List<FuelUsage> searchFuelUsagesByUserAndCarModel(User user, String model) {
        return fuelUsageRepository.findByUserAndCarModelContainingIgnoreCase(user, model);
    }

    public List<FuelUsage> findAllFuelUsagesByUser(User user) {
        return fuelUsageRepository.findByUser(user);
    }

}
