package com.example.edufy.services;

import com.example.edufy.VO.Customer;
import com.example.edufy.VO.Media;
import com.example.edufy.VO.MediaInteractions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EdufyUserServiceTest {
    private EdufyUserService edufyUserService = new EdufyUserService();
    private RestTemplate mockRestTemplate = mock(RestTemplate.class);

    private Customer customerTest;
    private Media mediaTest;
    private MediaInteractions mediaInteractionsTest;
    private List<MediaInteractions> testList = new ArrayList<>();


    @BeforeEach
    void setUp() {
        mediaInteractionsTest = new MediaInteractions(1,1,null,0);
        mediaTest = new Media(1,"music","testmusic","www.TestMusic.com", LocalDate.now());
        testList.add(mediaInteractionsTest);
        customerTest = new Customer(1,"huggoTest" , "huggo@Test.com",testList);
        edufyUserService.setRestTemplate(mockRestTemplate);
    }

    @Test
    void playMediaAndIncreaseTimesListenedTo_ShouldIncreasePlayCountWhenMediaInteractionsIsEqualMedia() {
        //given
        int idCustomer = 1;
        int idMedia = 1;

        String customerGetURL = "http://customer-service/api/customer/1";
        String mediaGetURL = "http://media-service/api/media/1";
        String customerPutURl = "http://customer-service/api/customer/updatecustomer/1";

        when(mockRestTemplate.getForObject(mediaGetURL,Media.class)).thenReturn(mediaTest);
        when(mockRestTemplate.getForObject(customerGetURL, Customer.class)).thenReturn(customerTest);


        //when
        edufyUserService.playAndUpdateListedToInCustomer(idCustomer,idMedia);

        //then
        verify(mockRestTemplate).put(customerPutURl,customerTest);
        assertEquals(1,mediaInteractionsTest.getTimesListenedTo());

    }

    @Test
    void playMediaAndIncreaseTimesListenedTo_ShouldAddAIndexInCustomerList() {
        //given
        int idCustomer = 1;
        int idMedia = 1;

        String customerGetURL = "http://customer-service/api/customer/1";
        String mediaGetURL = "http://media-service/api/media/1";
        String customerPutURl = "http://customer-service/api/customer/updatecustomer/1";

        mediaTest = new Media(2,"Podcast", "Sagon om", "www.pod.com", LocalDate.now());
        when(mockRestTemplate.getForObject(customerGetURL, Customer.class)).thenReturn(customerTest);
        when(mockRestTemplate.getForObject(mediaGetURL, Media.class)).thenReturn(mediaTest);

        //when
        edufyUserService.playAndUpdateListedToInCustomer(idCustomer,idMedia);

        //then
        verify(mockRestTemplate).put(customerPutURl,customerTest);
        assertEquals(2,customerTest.getMediaInteractions().size());

    }

    @Test
    void setRestTemplate() {
    }
}