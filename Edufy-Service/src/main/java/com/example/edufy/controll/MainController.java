package com.example.edufy.controll;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping("/api/edufy/getString")
    public String getString(){
        return "hej";
    }
}
