package com.CarManagement.CarMan.model;

//import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
public class MaintenanceTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title; //A short title or description of the maintenance task.

    private String description; // A more detailed description of the maintenance task.


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dueDate;

    private double cost;

    private String mechanicName;

    private String serviceProvider;

    private String notes;

    private boolean completed; // A boolean value indicating whether the task has been completed.

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate completionDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)  //@ManyToOne annotation is used to define the relationship between MaintenanceTask and Car, and the fetch attribute is set to LAZY to improve performance by only loading the associated Car entity when necessary
    @JoinColumn(name = "car_id", nullable = false)
    private Car car; //A reference to the car associated with the maintenance task.

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
