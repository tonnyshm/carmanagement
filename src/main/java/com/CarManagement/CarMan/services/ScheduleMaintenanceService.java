package com.CarManagement.CarMan.services;

import com.CarManagement.CarMan.helpers.ResourceNotFoundException;
import com.CarManagement.CarMan.model.Car;
import com.CarManagement.CarMan.model.ScheduleMaintenance;
import com.CarManagement.CarMan.model.User;
import com.CarManagement.CarMan.repository.ScheduleMaintenanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleMaintenanceService {

    @Autowired
    private ScheduleMaintenanceRepository scheduleMaintenanceRepository;

    public ScheduleMaintenance createScheduleMaintenance(ScheduleMaintenance scheduleMaintenance) {
        scheduleMaintenance.setScheduledDate(null);
        return scheduleMaintenanceRepository.save(scheduleMaintenance);
    }



    public List<ScheduleMaintenance> getAllScheduleMaintenances() {
        return scheduleMaintenanceRepository.findAll();
    }

    public ScheduleMaintenance getScheduleMaintenanceById(Long id) {
        return scheduleMaintenanceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ScheduleMaintenance", "id", id));
    }

    public List<ScheduleMaintenance> getScheduleMaintenancesByCarId(Long carId) {
        return scheduleMaintenanceRepository.findByCarId(carId);
    }
}

