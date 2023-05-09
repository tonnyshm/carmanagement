package com.CarManagement.CarMan.repository;

import com.CarManagement.CarMan.model.Car;
import com.CarManagement.CarMan.model.CarPhoto;
import com.CarManagement.CarMan.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarPhotoRepository extends JpaRepository<CarPhoto, Long> {
    Optional<CarPhoto> findById(Long id);
    List<CarPhoto> findByCarUser(User user);

    List<CarPhoto> getCarPhotosByCar(Car car);
    List<CarPhoto> findByCar(Car car);
}
