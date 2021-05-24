package com.assignment3.customer.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {
    private Long id;

    private String name;

    private String licenseNb;

    private String personalNb;

    private String phoneNb;

    private String address;
}
