package com.example.edufy.services;

import com.example.edufy.VO.Customer;
//import com.example.edufy.repositories.EdufyUserRepository;
import com.example.edufy.VO.Media;
import com.example.edufy.VO.MediaInteractions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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

    public Media getMediaVO() {
        //Har man med loadbalanced letar den inom sina services och man kan inte köra med localhost utan måste använda servicenamnet
        //    Customer customerVO = restTemplate.getForObject("http://127.0.0.1:6060/api/customer/1", Customer.class);
        //    Customer customerVO = restTemplate.getForObject("localhost:6060/api/customer/1", Customer.class);
        Media mediaVO = restTemplate.getForObject("http://media-service/api/media/1", Media.class);

        // restTemplate och getForObject för att köra get, verkar finnas metoder för alla crud operationer med restTemplate
        return mediaVO;
    }

    public Customer putCustomerVO() {
        //Har man med loadbalanced letar den inom sina services och man kan inte köra med localhost utan måste använda servicenamnet
        //    Customer customerVO = restTemplate.getForObject("http://127.0.0.1:6060/api/customer/1", Customer.class);
        //    Customer customerVO = restTemplate.getForObject("localhost:6060/api/customer/1", Customer.class);
        Customer customerVO = getCustomerVO();
        customerVO.setUserName("hej");
        customerVO.setEmailAdress("hej.com");
        restTemplate.put("http://customer-service/api/customer/updatecustomer/1", customerVO);

        // restTemplate och getForObject för att köra get, verkar finnas metoder för alla crud operationer med restTemplate
        return customerVO;
    }

    public MediaInteractions  postMediaInteractionsVO() {
        //Har man med loadbalanced letar den inom sina services och man kan inte köra med localhost utan måste använda servicenamnet
        //    Customer customerVO = restTemplate.getForObject("http://127.0.0.1:6060/api/customer/1", Customer.class);
        //    Customer customerVO = restTemplate.getForObject("localhost:6060/api/customer/1", Customer.class);
        MediaInteractions mediaInteractions = new MediaInteractions();
        mediaInteractions.setMediaId(100);
        mediaInteractions.setTimesListenedTo(1);
        mediaInteractions.setLikeStatus("like");
        restTemplate.postForEntity("http://customer-service/api/customer/addmediainteractions", mediaInteractions, MediaInteractions.class);

        // restTemplate och getForObject för att köra get, verkar finnas metoder för alla crud operationer med restTemplate
        return mediaInteractions;
    }

    @Override
    public Media playAndUpdateListedToInCustomer(int idCustomer, int idMedia){
        String customerGetURL = "http://customer-service/api/customer/" + idCustomer;
        String mediaGetURL = "http://media-service/api/media/" + idMedia;
        String customerPutURl = "http://customer-service/api/customer/updatecustomer/" + idCustomer;
        String mediaInteractionsPostURl = "http://customer-service/api/customer/addmediainteractions";

        Customer customerVO = restTemplate.getForObject(customerGetURL, Customer.class);
        Media mediaVO = restTemplate.getForObject(mediaGetURL,Media.class);

        customerVO.setUserName("kevin");
        customerVO.setEmailAdress("new kevin");

        for (MediaInteractions m: customerVO.getMediaInteractions()) {
            if (m.getMediaId() == mediaVO.getId()){
                m.increasePlayCount();
                restTemplate.put(customerPutURl,customerVO);
                return mediaVO;
            }
        }

        MediaInteractions mediaInteractionsVO = new MediaInteractions("empty",0,customerVO);


        mediaInteractionsVO.setMediaInteractionId(customerVO.getMediaInteractions().size()+1);
        mediaInteractionsVO.setMediaId(mediaVO.getId());
        mediaInteractionsVO.increasePlayCount();
        //restTemplate.postForEntity(mediaInteractionsPostURl,mediaInteractionsVO, MediaInteractions.class);
        customerVO.getMediaInteractions().add(mediaInteractionsVO);

        restTemplate.put(customerPutURl,customerVO);
        return mediaVO;
    }

    @Override
    public MediaInteractions rateMedia(int customerId, int mediaId, String likeStatus) {
        if(customerId <= 0) {
            throw new IllegalArgumentException("customerId must be greater than 0");
        }

        String getCustomerURL = "http://customer-service/api/customer/" + customerId;
        String getMediaURL = "http://media-service/api/media/" + mediaId;
        String customerPutURl = "http://customer-service/api/customer/updatecustomer/" + customerId;
        String mediaInteractionsURL = "http://customer-service/api/customer/addmediainteractions";

        try {
            Customer customerVO = restTemplate.getForObject(getCustomerURL, Customer.class);
            if (customerVO == null) {
                throw new RuntimeException("Customer not found");
            }
            Media mediaVO = restTemplate.getForObject(getMediaURL, Media.class);
            if (mediaVO == null) {
                throw new RuntimeException("Media not found");
            }

            MediaInteractions existingInteraction = customerVO.getMediaInteractions().stream()
                    .filter(m -> m.getMediaId() == mediaVO.getId())
                    .findFirst()
                    .orElse(null);

            if (existingInteraction != null) {
                existingInteraction.setLikeStatus(likeStatus);
                restTemplate.put(customerPutURl, customerVO);
                return existingInteraction;
            } else {
                MediaInteractions newInteraction = new MediaInteractions("empty", 0, customerVO);
                newInteraction.setMediaId(mediaVO.getId());
                newInteraction.setLikeStatus(likeStatus);
                newInteraction.setCustomer(customerVO);

                customerVO.getMediaInteractions().add(newInteraction);

                restTemplate.postForEntity(mediaInteractionsURL, newInteraction, MediaInteractions.class);

                restTemplate.put(customerPutURl, customerVO);

                return newInteraction;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error while processing media interaction", e);
        }

    }

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

}
