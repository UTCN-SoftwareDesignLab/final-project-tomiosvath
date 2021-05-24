package com.assignment3.rental.model;

import com.assignment3.car.model.Car;
import com.assignment3.customer.model.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "carId")
    private Car car;

    @ManyToOne
    @JoinColumn(name = "customerId")
    private Customer customer;

    private LocalDate fromDate;

    private LocalDate untilDate;

    private Float price;

    private String location;

}
