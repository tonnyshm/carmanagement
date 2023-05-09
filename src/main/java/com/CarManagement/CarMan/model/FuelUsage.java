package com.CarManagement.CarMan.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class FuelUsage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int odometer;
    private double fuelVolume;
    private double pricePerUnit;
    private double totalPrice;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)  //@ManyToOne annotation is used to define the relationship between MaintenanceTask and Car, and the fetch attribute is set to LAZY to improve performance by only loading the associated Car entity when necessary
    @JoinColumn(name = "car_id", nullable = false)
    private Car car; //A reference to the car associated with the Fuel usage.

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;


}
