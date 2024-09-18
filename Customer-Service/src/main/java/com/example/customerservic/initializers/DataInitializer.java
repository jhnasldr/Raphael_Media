//package com.example.customerservic.initializers;
//
//import com.example.customerservic.entities.Customer;
//import com.example.customerservic.repositories.CustomerRepository;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//@Component
//public class DataInitializer implements CommandLineRunner {
//
//    private final CustomerRepository customerRepository;
//
//    public DataInitializer(CustomerRepository customerRepository) {
//        this.customerRepository = customerRepository;
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        // Example data initialization
////        if (customerRepository.count() == 0) {
////            customerRepository.save(new Customer("testuser", "testpassword"));
////        }
//    }
//}