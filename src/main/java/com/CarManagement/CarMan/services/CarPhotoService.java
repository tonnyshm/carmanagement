package com.CarManagement.CarMan.services;

import com.CarManagement.CarMan.model.Car;
import com.CarManagement.CarMan.model.CarPhoto;
import com.CarManagement.CarMan.model.User;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface CarPhotoService {

    void savePhoto(MultipartFile file, Car car, User user) throws IOException;

    CarPhoto getPhotoById(Long id);
    void downloadCarPhoto(Long id, HttpServletResponse response) throws IOException;
    void deleteCarPhoto(Long id);
    List<CarPhoto> getCarPhotosByCar(Car car);
    CarPhoto getCarPhotoById(Long id);

}
