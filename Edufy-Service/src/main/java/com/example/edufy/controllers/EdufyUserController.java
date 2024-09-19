package com.example.edufy.controllers;

import com.example.edufy.DTOs.MediaResponseDTO;
import com.example.edufy.VO.*;
import com.example.edufy.services.EdufyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/edufy")
public class EdufyUserController {

    @Autowired
    EdufyUserService edufyUserService;



    //Bara test här, ska göras riktigt med responseEntity, testning, namngivning mm
    //får fram kund ett anna
//    @PreAuthorize("hasRole('user')")
//    @GetMapping("/getcustomervo")
//    public Customer getVo(){
//        return edufyUserService.getCustomerVO();
//    }


    @PreAuthorize("hasRole('user')") //Finns inte i Postman
    @GetMapping("/getallmediadto")
    public ResponseEntity<List<Media>> getAllmediaDTO() {
        return ResponseEntity.ok(edufyUserService.getAllMediaDTO());
    }

    @PreAuthorize("hasRole('user')") //Finns inte i Postman (jag förstår att de är ett test men ändå)

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Test");
    }

    @PreAuthorize("hasRole('user')")
    @GetMapping("/recommendations/{customerId}")
    public ResponseEntity<List<MediaResponseDTO>> getRecommendedMedia(@PathVariable int customerId) {
        List<MediaResponseDTO> recommendations = edufyUserService.getRecommendedMedia(customerId);
        return ResponseEntity.ok(recommendations);
    }

    @PreAuthorize("hasRole('user')")
    @PutMapping("/playmedia")
    public ResponseEntity<String> playMediaAndIncreaseTimesListenedTo(@RequestBody MediaRequstBody mediaRequstBody) {
        Media playMedia = edufyUserService.playAndUpdateListedToInCustomer(mediaRequstBody.getCustomerId(), mediaRequstBody.getMediaId());
        return new ResponseEntity<>("This " + playMedia.getMediaType() + " is playing \n" + playMedia.getURL(), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('user')")
    @GetMapping("/getmostplayedmedia/{customerId}")
    public ResponseEntity<List<MediaResponseDTO>> getMostPlayedMedia(@PathVariable  int customerId) {
        return ResponseEntity.ok(edufyUserService.getMostPlayedMediaForUserById(customerId));
    }

    @PreAuthorize("hasRole('user')")
    @PostMapping("/ratemedia")
    public ResponseEntity<String> rateMedia(@RequestBody CustomerMediaRequestBody requestBody){
        MediaInteractions result = edufyUserService.rateMedia(requestBody.getCustomerId(), requestBody.getMediaId(), requestBody.getLikeStatus());
        String message = "Customer with id: " + requestBody.getCustomerId() + " set " + requestBody.getLikeStatus() + " on media with id: " + requestBody.getMediaId();
        return ResponseEntity.ok(message);
    }
}
