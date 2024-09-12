package com.example.edufy.controllers;

import com.example.edufy.VO.*;
import com.example.edufy.services.EdufyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/playmedia")
    public ResponseEntity<String> playMediaAndIncreaseTimesListenedTo(@RequestBody MediaRequstBody mediaRequstBody){
        Media playMedia = edufyUserService.playAndUpdateListedToInCustomer(mediaRequstBody.getCustomerId(), mediaRequstBody.getMediaId());
        return new ResponseEntity<>("This " + playMedia.getMediaType() + " is playing \n" + playMedia.getURL(), HttpStatus.OK);

    }

    @PostMapping("/ratemedia")
    public ResponseEntity<String> rateMedia(@RequestBody CustomerMediaRequestBody requestBody){
        MediaInteractions result = edufyUserService.rateMedia(requestBody.getCustomerId(), requestBody.getMediaId(), requestBody.getLikeStatus());
        String message = "Customer with id: " + requestBody.getCustomerId() + " set " + requestBody.getLikeStatus() + " on media with id: " + requestBody.getMediaId();
        return ResponseEntity.ok(message);

    }
}
