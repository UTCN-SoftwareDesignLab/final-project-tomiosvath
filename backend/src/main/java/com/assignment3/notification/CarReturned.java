package com.assignment3.notification;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class CarReturned {
    private String carManufacturer;
    private String carModel;
    private String customerName;
}
