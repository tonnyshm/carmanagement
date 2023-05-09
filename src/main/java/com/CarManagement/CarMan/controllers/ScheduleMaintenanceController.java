package com.CarManagement.CarMan.controllers;

import com.CarManagement.CarMan.model.Car;
import com.CarManagement.CarMan.model.ScheduleMaintenance;
import com.CarManagement.CarMan.services.CarService;
import com.CarManagement.CarMan.services.ScheduleMaintenanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/schedule////maintenance")
public class ScheduleMaintenanceController {

    @Autowired
    private ScheduleMaintenanceService scheduleMaintenanceService;

    @Autowired
    private CarService carService;

    @PostMapping
    public ScheduleMaintenance createScheduleMaintenance(@RequestBody ScheduleMaintenance scheduleMaintenance) {
        return scheduleMaintenanceService.createScheduleMaintenance(scheduleMaintenance);
    }

    @GetMapping
    public List<ScheduleMaintenance> getAllScheduleMaintenances() {
        return scheduleMaintenanceService.getAllScheduleMaintenances();
    }

    @GetMapping("/{id}")
    public ScheduleMaintenance getScheduleMaintenanceById(@PathVariable Long id) {
        return scheduleMaintenanceService.getScheduleMaintenanceById(id);
    }

    @GetMapping("/cars/{carId}")
    public List<ScheduleMaintenance> getScheduleMaintenancesByCarId(@PathVariable Long carId) {
        return scheduleMaintenanceService.getScheduleMaintenancesByCarId(carId);
    }

    @GetMapping("/form")
    public ModelAndView getScheduleMaintenanceForm() {
        ModelAndView modelAndView = new ModelAndView("schedule_maintenance_form");
        List<Car> cars = carService.getCarsForCurrentUser();
        modelAndView.addObject("cars", cars);
        modelAndView.addObject("scheduleMaintenance", new ScheduleMaintenance());
        return modelAndView;
    }

}
