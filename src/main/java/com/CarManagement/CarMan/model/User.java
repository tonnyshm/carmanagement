package com.CarManagement.CarMan.model;

//import jakarta.persistence.*;
import lombok.Data;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Validated
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String first_name;
    private String last_name;

    @Column(name = "username",unique = true)
    private String username;

    private String address;
    private String phoneNumber;
    @Column(name = "email", unique = true, length = 45)
    private String email;
    @Column(name = "password")
    private String password;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

   @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true) //The CascadeType.ALL option specifies that all Hibernate actions (including delete) should be propagated from the parent entity (Car) to the child entity (MaintenanceTask).The orphanRemoval attribute is set to true, which means that any MaintenanceTask entity that is no longer associated with a Car entity will be automatically removed from the database.
   private Set<Car> cars;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CarPhoto> carPhotos = new ArrayList<>();



}
