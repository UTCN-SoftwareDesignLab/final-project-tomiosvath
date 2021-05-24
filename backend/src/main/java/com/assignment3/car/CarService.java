package com.assignment3.car;

import com.assignment3.car.model.Car;
import com.assignment3.car.model.dto.CarDetailsDTO;
import com.assignment3.image.ImageService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;

    private final ImageService imageService;

    private final CarMapperImplementation carMapperImplementation;

    public List<CarDetailsDTO> findAll(){
        return carRepository.findAll().stream()
                .map(carMapperImplementation::toCarDetailsDTO)
                .collect(Collectors.toList());
    }

    public Car findById(Long id){
        return carRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No car with id: " + id));
    }

    public void create(Car car){
        carRepository.save(car);
    }

    public void delete(Long id){
        Car car = findById(id);
        carRepository.delete(findById(id));
        imageService.delete(car.getImage().getId());
    }

    public void setRentedStatus(Car car, boolean value){
        car.setRented(value);
        carRepository.save(car);
    }


    public void returnCar(Long id) {
        Car car = findById(id);
        car.setRented(false);
        carRepository.save(car);
    }
}
