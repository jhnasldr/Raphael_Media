package com.example.customerservic.controllers;

import com.example.customerservic.entities.MediaInteractions;
import com.example.customerservic.services.MediaInteractionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customer/")
public class MediaInteractionsController {

    @Autowired
    private MediaInteractionsService mediaInteractionsService;

    @PostMapping("addmediainteractions") //Finns inte postman
    public ResponseEntity<String> addMediaInteraction(@RequestBody MediaInteractions mediaInteractions) {
        mediaInteractionsService.addMediaInteraction(mediaInteractions);
        return new ResponseEntity<>("MediaInteractions is added", HttpStatus.OK);
    }
}
