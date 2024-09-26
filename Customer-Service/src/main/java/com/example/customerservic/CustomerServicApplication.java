package com.example.customerservic;

import org.apache.log4j.BasicConfigurator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CustomerServicApplication {

    public static void main(String[] args) {
        BasicConfigurator.configure();
        SpringApplication.run(CustomerServicApplication.class, args);
    }

}
