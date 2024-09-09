package com.example.customerservic.controllers;

import com.example.customerservic.entities.Customer;
import com.example.customerservic.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;


@RestController
@RequestMapping("/api/customer/")
public class CustomerController {

    @Autowired
    private CustomerService customerService;


    @GetMapping("{customerId}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable int customerId) {
        Optional<Customer> customer = customerService.findCustomerById(customerId);
        return ResponseEntity.ok(customer.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));

    }

    @PostMapping("addcustomer")
    public ResponseEntity<String> addCustomer(@RequestBody Customer customer){
        customerService.addCustomer(customer);
        return new ResponseEntity<>("Customer is added", HttpStatus.OK);
    }

    @DeleteMapping("deletecustomer/{id}")
    public ResponseEntity<String> deleteCustomerByID(@PathVariable int id){
        customerService.deleteCustomerById(id);
        return new ResponseEntity<>("Customer is delete", HttpStatus.OK);
    }
}
