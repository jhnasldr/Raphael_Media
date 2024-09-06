package com.example.customerservic.controllers;

import com.example.customerservic.entities.Customer;
import com.example.customerservic.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;


    @GetMapping("/api/customer/{customerId}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable int customerId) {
        Optional<Customer> customer = customerService.findCustomerById(customerId);
        //Returns correct HTTP-answer depending on whether the customer was found or not
        return customer.map(c -> ResponseEntity.ok().body(c)).orElse(ResponseEntity.notFound().build());
    }
}
