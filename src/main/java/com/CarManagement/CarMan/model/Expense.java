package com.CarManagement.CarMan.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate expenseDate;

    private String description;
    private double amount;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)  //@ManyToOne annotation is used to define the relationship between MaintenanceTask and Car, and the fetch attribute is set to LAZY to improve performance by only loading the associated Car entity when necessary
    @JoinColumn(name = "car_id", nullable = false)
    private Car car; //A reference to the car associated with the Fuel usage.

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
