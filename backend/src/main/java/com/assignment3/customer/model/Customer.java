package com.assignment3.customer.model;

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
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 512, nullable = false)
    private String name;

    @Column(length = 10, nullable = false)
    private String licenseNb;

    @Column(length = 13, nullable = false)
    private String personalNb;

    @Column(length = 10, nullable = false)
    private String phoneNb;

    private String address;
}
