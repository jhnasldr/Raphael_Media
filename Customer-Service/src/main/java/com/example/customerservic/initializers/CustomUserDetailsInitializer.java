//package com.example.customerservic.initializers;
//
//import com.example.customerservic.entities.Customer;
//import com.example.customerservic.exceptions.ResourceNotFoundException;
//import com.example.customerservic.repositories.CustomerRepository;
//import jakarta.annotation.PostConstruct;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.stereotype.Component;
//
//@Component
//public class CustomUserDetailsInitializer {
//
//    private final CustomerRepository customerRepository;
//    private final UserDetailsService userDetailsService;
//
//    @Autowired
//    public CustomUserDetailsInitializer(CustomerRepository customerRepository, UserDetailsService userDetailsService) {
//        this.customerRepository = customerRepository;
//        this.userDetailsService = userDetailsService;
//    }
//
//    @PostConstruct
//    public void init() {
//        Customer customer = customerRepository.findById(1)
//                .orElseThrow(() -> new ResourceNotFoundException("Customer", "id", 1));
//
//        UserDetails user = User.withDefaultPasswordEncoder()
//                .username(customer.getUserName())
//                .password(customer.getUserName())
//                .roles("user")
//                .build();
//
//        ((InMemoryUserDetailsManager) userDetailsService).createUser(user);
//    }
//}