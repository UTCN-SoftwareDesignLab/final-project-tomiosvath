package com.assignment3.rental.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RentalDisplayDto {
    private String customer;
    private String manufacturer;
    private String model;
    private String from;
    private String until;
    private String price;
    private String location;
}
