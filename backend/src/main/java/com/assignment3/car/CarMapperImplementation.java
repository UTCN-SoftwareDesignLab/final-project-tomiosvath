package com.assignment3.car;

import com.assignment3.car.model.Car;
import com.assignment3.car.model.dto.CarDetailsDTO;
import com.assignment3.image.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CarMapperImplementation {

    public CarDetailsDTO toCarDetailsDTO(Car car){
        return CarDetailsDTO.builder()
                .id(car.getId())
                .manufacturer(car.getManufacturer())
                .model(car.getModel())
                .year(car.getYear())
                .location(car.getLocation())
                .price(car.getPrice())
                .rented(String.valueOf(car.isRented()))
                .name(car.getImage().getName())
                .type(car.getImage().getType())
                .picByte(ImageService.decompressBytes(car.getImage().getPicByte()))
                .build();
    }
}
