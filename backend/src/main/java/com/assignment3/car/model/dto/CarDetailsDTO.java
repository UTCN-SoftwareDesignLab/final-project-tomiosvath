package com.assignment3.car.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CarDetailsDTO {
    private Long id;
    private String manufacturer;
    private String model;
    private int year;
    private String location;
    private int price;
    private String rented;

    //image info
    private String name;
    private String type;
    private byte[] picByte;
}
