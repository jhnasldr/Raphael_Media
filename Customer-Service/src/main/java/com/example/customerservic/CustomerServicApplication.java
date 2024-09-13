package com.example.customerservic;

import org.apache.log4j.BasicConfigurator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CustomerServicApplication {

    public static void main(String[] args) {
        BasicConfigurator.configure();  // Behövs för loggning, tror det är för att vi har lagt alla projekt i en och samma mapp
        SpringApplication.run(CustomerServicApplication.class, args);
    }

}
