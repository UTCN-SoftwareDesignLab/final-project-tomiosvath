package com.assignment3.customer;

import com.assignment3.customer.model.dto.CustomerDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.assignment3.UrlMapping.CUSTOMER;

@RestController
@RequestMapping(CUSTOMER)
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    public List<CustomerDTO> allCustomers(){
        return customerService.findAll();
    }

    @PostMapping
    public void create(@RequestBody CustomerDTO customerDTO){
        customerService.create(customerDTO);
    }

    @PatchMapping
    public void edit(@RequestBody CustomerDTO customerDTO){
        customerService.edit(customerDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        customerService.delete(id);
    }
}
