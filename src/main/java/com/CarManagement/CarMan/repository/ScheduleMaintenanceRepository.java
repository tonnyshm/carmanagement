package com.CarManagement.CarMan.repository;

import com.CarManagement.CarMan.model.ScheduleMaintenance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleMaintenanceRepository extends JpaRepository<ScheduleMaintenance, Long> {

    List<ScheduleMaintenance> findByCarId(Long carId);
}

