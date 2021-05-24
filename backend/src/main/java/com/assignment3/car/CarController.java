package com.assignment3.car;

import com.assignment3.car.model.Car;
import com.assignment3.car.model.dto.CarDetailsDTO;
import com.assignment3.image.ImageService;
import com.assignment3.image.model.Image;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static com.assignment3.UrlMapping.CAR;

@RestController
@RequiredArgsConstructor
@RequestMapping(CAR)
public class CarController {

    private final CarService carService;

    private final ImageService imageService;

    @GetMapping
    public List<CarDetailsDTO> allCars(){
        return carService.findAll();
    }


    @PostMapping
    public void create(@RequestParam("manufacturer") String manufacturer, @RequestParam("model") String model,
                       @RequestParam("year") String year, @RequestParam("location") String location,
                       @RequestParam("price") String price, @RequestParam("imageFile") MultipartFile file) throws IOException {

        Image image = imageService.upload(file);
        Car car = Car.builder()
                .manufacturer(manufacturer)
                .model(model)
                .year(Integer.parseInt(year))
                .location(location)
                .price(Integer.parseInt(price))
                .image(image)
                .build();

        carService.create(car);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        carService.delete(id);
    }

    @PostMapping("/return/{id}")
    public void returnCar(@PathVariable Long id){
        carService.returnCar(id);
    }
}
