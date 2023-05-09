package com.CarManagement.CarMan.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class ScheduleMaintenance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;
    private String taskName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime scheduledDate;

}

