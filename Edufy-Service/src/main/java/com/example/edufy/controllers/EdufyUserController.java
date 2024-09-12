package com.example.edufy.controllers;

import com.example.edufy.VO.Customer;
import com.example.edufy.VO.Media;
import com.example.edufy.VO.MediaInteractions;
import com.example.edufy.VO.MediaRequstBody;
import com.example.edufy.services.EdufyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/edufy")
public class EdufyUserController {

    @Autowired
    EdufyUserService edufyUserService;

    //Bara test här, ska göras riktigt med responseEntity, testning, namngivning mm
    //får fram kund ett anna
    @GetMapping("/getcustomervo")
    public Customer getVo(){
        return edufyUserService.getCustomerVO();
    }

    @GetMapping("/getmediavo")
    public Media getmediaVo(){
        return edufyUserService.getMediaVO();
    }

    @GetMapping("/getallmediadto")
    public ResponseEntity<List<Media>> getAllmediaDTO() {
        return ResponseEntity.ok(edufyUserService.getAllMediaDTO());
    }

    @GetMapping("/recommendations/{customerId}")
    public ResponseEntity<List<Media>> getRecommendedMedia(@PathVariable int customerId) {
        Customer customer = edufyUserService.getCustomerData(customerId);
        List<Media> recommendations = edufyUserService.getRecommendedMedia(customerId);
        return ResponseEntity.ok(recommendations);
    }

    @PutMapping("/putvo")
    public ResponseEntity<Customer> update(){
        Customer update = edufyUserService.putCustomerVO();
        return ResponseEntity.ok(update);
    }

    @PostMapping("/postMI")
    public ResponseEntity<MediaInteractions> addMI(){
        MediaInteractions mediaInteractions = edufyUserService.postMediaInteractionsVO();
        return ResponseEntity.ok(mediaInteractions);
    }


    @PutMapping("/playmedia")
    public ResponseEntity<String> playMediaAndIncreaseTimesListenedTo(@RequestBody MediaRequstBody mediaRequstBody){
        Media playMedia = edufyUserService.playAndUpdateListedToInCustomer(mediaRequstBody.getCustomerId(), mediaRequstBody.getMediaId());
        return new ResponseEntity<>("This " + playMedia.getMediaType() + " is playing \n" + playMedia.getURL(), HttpStatus.OK);

    }
}
