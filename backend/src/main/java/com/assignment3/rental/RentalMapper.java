package com.assignment3.rental;

import com.assignment3.Constants;
import com.assignment3.rental.model.Rental;
import com.assignment3.rental.model.dto.RentalDisplayDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RentalMapper {

    RentalDisplayDto toDTO(Rental rental){
        return RentalDisplayDto.builder()
                .customer(rental.getCustomer().getName())
                .location(rental.getLocation())
                .from(rental.getFromDate().format(Constants.formatter))
                .until(rental.getUntilDate().format(Constants.formatter))
                .manufacturer(rental.getCar().getManufacturer())
                .model(rental.getCar().getModel())
                .price(String.valueOf(rental.getPrice()))
                .build();
    }
}
