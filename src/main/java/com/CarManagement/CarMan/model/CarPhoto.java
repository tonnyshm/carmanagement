package com.CarManagement.CarMan.model;

import com.CarManagement.CarMan.model.Car;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class CarPhoto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private byte[] data;

    private String contentType;

    private String base64Image;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
