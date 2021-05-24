package com.assignment3.car.model;

import com.assignment3.image.model.Image;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 512, nullable = false)
    private String manufacturer;

    @Column(length = 512, nullable = false)
    private String model;

    private int year;

    @Column(length = 512, nullable = false)
    private String location;

    @OneToOne
    @JoinColumn(name = "imageId")
    private Image image;

    private int price;

    @Column(columnDefinition = "boolean default false")
    private boolean rented;
}
