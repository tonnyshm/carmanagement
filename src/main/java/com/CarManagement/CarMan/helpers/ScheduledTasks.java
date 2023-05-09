package com.CarManagement.CarMan.helpers;

import com.CarManagement.CarMan.model.ScheduleMaintenance;
import com.CarManagement.CarMan.services.ScheduleMaintenanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class ScheduledTasks {

    @Autowired
    private ScheduleMaintenanceService scheduleMaintenanceService;

    @Scheduled(cron = "0 0 0 1 */3 ?") //schedule task for 3 month
    public void scheduleMaintenance() {
        List<ScheduleMaintenance> scheduleMaintenances = scheduleMaintenanceService.getAllScheduleMaintenances();
        for (ScheduleMaintenance scheduleMaintenance : scheduleMaintenances) {
            if (scheduleMaintenance.getScheduledDate() == null || scheduleMaintenance.getScheduledDate().isBefore(LocalDateTime.now())) {
                // Schedule maintenance for car
                scheduleMaintenance.setScheduledDate(LocalDateTime.now().plusMonths(3));
                scheduleMaintenanceService.createScheduleMaintenance(scheduleMaintenance);
            }
        }
    }
}
