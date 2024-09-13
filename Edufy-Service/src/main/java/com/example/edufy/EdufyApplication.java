package com.example.edufy;

import org.apache.log4j.BasicConfigurator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class EdufyApplication {

    public static void main(String[] args) {
        BasicConfigurator.configure();
        SpringApplication.run(EdufyApplication.class, args);
    }

    @Bean
    @LoadBalanced
    //har man med loadbalanced så går den via sevicerna så den vill ha service namn ist för hela endpoint för resttremplate, ex för customer vill den ha
//   "http://customer-service/customer/1" istället för "http://127.0.0.1:6060/customer/1" 
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
