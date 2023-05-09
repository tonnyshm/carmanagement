package com.CarManagement.CarMan.services;

import com.CarManagement.CarMan.model.Car;
import com.CarManagement.CarMan.model.CarPhoto;
import com.CarManagement.CarMan.model.User;
import com.CarManagement.CarMan.repository.CarPhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CarPhotoServiceImpl implements CarPhotoService {

    @Autowired
    private CarPhotoRepository carPhotoRepository;

    @Override
    public void savePhoto(MultipartFile file, Car car , User user) throws IOException {
        CarPhoto photo = new CarPhoto();
        photo.setCar(car);
        photo.setUser(user);
        photo.setContentType(file.getContentType());
        photo.setData(file.getBytes());
        carPhotoRepository.save(photo);
    }

    @Override
    public CarPhoto getPhotoById(Long id) {
        return carPhotoRepository.findById(id).orElse(null);
    }

    @Override
    public void downloadCarPhoto(Long id, HttpServletResponse response) throws IOException {
        // Find the car photo by ID
        Optional<CarPhoto> optionalCarPhoto = carPhotoRepository.findById(id);
        if (optionalCarPhoto.isPresent()) {
            CarPhoto carPhoto = optionalCarPhoto.get();

            // Determine the content type based on the file extension
            String contentType;
            String randomString = UUID.randomUUID().toString();
            String fileName = randomString;
            if (fileName.endsWith(".jpg") || fileName.endsWith(".jpeg")) {
                contentType = "image/jpeg";
            } else if (fileName.endsWith(".png")) {
                contentType = "image/png";
            } else {
                throw new IllegalArgumentException("Unsupported file type");
            }

            // Set the content type and headers for the download response
            response.setContentType(contentType);
            response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"");

            // Retrieve the BLOB data from the database
            byte[] data = carPhoto.getData();

            // Write the BLOB data to the response output stream
            try (OutputStream outputStream = response.getOutputStream()) {
                outputStream.write(data);
                outputStream.flush();
            }
        }
    }

    @Override
    public CarPhoto getCarPhotoById(Long id) {
        return carPhotoRepository.findById(id).orElse(null);
    }

    @Override
    public List<CarPhoto> getCarPhotosByCar(Car car) {
        return carPhotoRepository.findByCar(car);
    }

    @Override
    public void deleteCarPhoto(Long id) {
        Optional<CarPhoto> optionalCarPhoto = carPhotoRepository.findById(id);
        if (optionalCarPhoto.isPresent()) {
            CarPhoto carPhoto = optionalCarPhoto.get();
            carPhotoRepository.delete(carPhoto);
        }
    }
}

