package com.example.raphael_media;

import org.apache.log4j.BasicConfigurator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RaphaelMediaApplication {

    public static void main(String[] args) {
        BasicConfigurator.configure();
        SpringApplication.run(RaphaelMediaApplication.class, args);
    }

}
