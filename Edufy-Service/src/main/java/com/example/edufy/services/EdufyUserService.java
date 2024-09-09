package com.example.edufy.services;

import com.example.edufy.VO.Customer;
import com.example.edufy.repositories.EdufyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EdufyUserService {

    @Autowired
    private EdufyUserRepository edufyUserRepository;
    @Autowired
    private RestTemplate restTemplate;


    //Test för forskning, det saknas enhetstestning för den här mm
    public Customer getCustomerVO() {
        //Har man med loadbalanced letar den inom sina services och man kan inte köra med localhost utan måste använda servicenamnet
        //    Customer customerVO = restTemplate.getForObject("http://127.0.0.1:6060/api/customer/1", Customer.class);
        //    Customer customerVO = restTemplate.getForObject("localhost:6060/api/customer/1", Customer.class);
        Customer customerVO = restTemplate.getForObject("http://customer-service/api/customer/1", Customer.class);

        // restTemplate och getForObject för att köra get, verkar finnas metoder för alla crud operationer med restTemplate
        return customerVO;
    }

}
