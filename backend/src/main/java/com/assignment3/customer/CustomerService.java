package com.assignment3.customer;

import com.assignment3.customer.model.Customer;
import com.assignment3.customer.model.dto.CustomerDTO;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    private final CustomerMapper customerMapper;

    public List<CustomerDTO> findAll(){
        return customerRepository.findAll().stream()
                .map(customerMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Customer findById(Long id){
        return customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No customer with id: " + id));
    }

    public void create(CustomerDTO customerDTO){
        customerRepository.save(customerMapper.fromDTO(customerDTO));
    }

    public void delete(Long id){
        customerRepository.delete(findById(id));
    }

    public void edit(CustomerDTO customerDTO){
        customerRepository.save(customerMapper.fromDTO(customerDTO));
    }

    public Customer findByNameAndPersonalNb(String name, String personalNb){
        return customerRepository.findByNameAndPersonalNb(name, personalNb)
                .orElseThrow(() -> new EntityNotFoundException("Can't find customer"));
    }
}
