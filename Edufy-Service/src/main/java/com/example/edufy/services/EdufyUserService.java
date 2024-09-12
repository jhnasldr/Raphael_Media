package com.example.edufy.services;

import com.example.edufy.VO.Customer;
//import com.example.edufy.repositories.EdufyUserRepository;
import com.example.edufy.VO.Media;
import com.example.edufy.VO.MediaInteractions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class EdufyUserService implements EdufyServiceInterface {
//
//    @Autowired
//    private EdufyUserRepository edufyUserRepository;
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


    @Override
    public Media playAndUpdateListedToInCustomer(int idCustomer, int idMedia){
        String customerGetURL = "http://customer-service/api/customer/" + idCustomer;
        String mediaGetURL = "http://media-service/api/media/" + idMedia;
        String customerPutURl = "http://customer-service/api/customer/updatecustomer/" + idCustomer;

        Customer customerVO = restTemplate.getForObject(customerGetURL, Customer.class);
        Media mediaVO = restTemplate.getForObject(mediaGetURL,Media.class);


        for (MediaInteractions m: customerVO.getMediaInteractions()) {
            if (m.getMediaId() == mediaVO.getId()){
                m.increasePlayCount();
                restTemplate.put(customerPutURl,customerVO);
                return mediaVO;
            }
        }

        MediaInteractions mediaInteractionsVO = new MediaInteractions();
        mediaInteractionsVO.setLikeStatus("empty");
        mediaInteractionsVO.increasePlayCount();
        mediaInteractionsVO.setMediaId(mediaVO.getId());
        mediaInteractionsVO.setCustomer(customerVO);
        customerVO.getMediaInteractions().add(mediaInteractionsVO);
        System.out.println(customerVO.getMediaInteractions().size());
        restTemplate.put(customerPutURl,customerVO);

        return mediaVO;

    }

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

}
