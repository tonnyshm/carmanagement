package com.CarManagement.CarMan.model;

//import jakarta.persistence.*;
import lombok.Data;
//import org.hibernate.query.sql.internal.ParameterRecognizerImpl;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String  make;
    private String model;
    private String vinNumber;
    private int year;
    private String licensePlate;
    private int currentMileage;

    @Enumerated(EnumType.STRING)
    private FuelType fuelType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "car")
    private Set<MaintenanceTask> maintenanceTasks;

    @OneToMany(mappedBy = "car")
    private Set<Expense > expenses ;

    @OneToMany(mappedBy = "car")
    private Set<FuelUsage > fuelUsages ;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CarPhoto> carPhotos = new ArrayList<>();


    private java.sql.Date createdAt;
    private java.sql.Date updatedAt;

}
