package com.assignment3.rental.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RentalDTO {
    private Long id;
    private Long carId;
    private String customerName;
    private String cnp;
    private String from;
    private String until;
    private String price;
    private String location;
}
