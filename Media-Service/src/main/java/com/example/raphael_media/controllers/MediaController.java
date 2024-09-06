package com.example.raphael_media.controllers;

import com.example.raphael_media.entities.Media;
import com.example.raphael_media.services.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/media/")
public class MediaController {

    @Autowired
    MediaService mediaService;

    @GetMapping("getallmedia")
    ResponseEntity<List<Media>> getAllMedia() {
        return ResponseEntity.ok(mediaService.getAllMedia());
    }

    @PostMapping("addnewmedia")
    ResponseEntity<String> addNewMedia(@RequestBody Media media) {
        mediaService.addNewMedia(media);
        return ResponseEntity.ok("New media created");
    }

}
