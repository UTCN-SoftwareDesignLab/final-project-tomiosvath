package com.assignment3.customer;

import com.assignment3.customer.model.Customer;
import com.assignment3.customer.model.dto.CustomerDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    Customer fromDTO(CustomerDTO customerDTO);

    CustomerDTO toDTO(Customer customer);
}
