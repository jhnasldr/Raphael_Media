package com.example.customerservic.controllers;

import com.example.customerservic.entities.Customer;
import com.example.customerservic.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("hasRole('admin')")
    @PostMapping("addcustomer")
    public ResponseEntity<String> addCustomer(@RequestBody Customer customer) {
        customerService.addCustomer(customer);
        return new ResponseEntity<>("Customer is added", HttpStatus.OK);
    }

    @PreAuthorize("hasRole('admin')")
    @DeleteMapping("deletecustomer/{id}")
    public ResponseEntity<String> deleteCustomerByID(@PathVariable int id) {
        customerService.deleteCustomerById(id);
        return new ResponseEntity<>("Customer is delete", HttpStatus.OK);
    }

    @PutMapping("updatecustomer/{customerId}")
    public ResponseEntity<String> updateCustomer(@PathVariable int customerId, @RequestBody Customer customer) {
        customerService.updateCustomer(customerId, customer);
        return new ResponseEntity<>("Customer with id " + customerId + " is updated", HttpStatus.OK);
    }
}
