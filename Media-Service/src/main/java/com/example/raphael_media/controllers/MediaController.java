package com.example.raphael_media.controllers;

import com.example.raphael_media.DTOs.MediaDTO;
import com.example.raphael_media.entities.Media;
import com.example.raphael_media.services.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

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

    @PutMapping("updateMedia/{id}")
    public ResponseEntity<String> updateMedia(@PathVariable int id, @RequestBody Media media) {
        mediaService.updateMedia(id, media);
        return new ResponseEntity<>("Media is updated", HttpStatus.OK);
    }

    @DeleteMapping("deletemedia/{id}")
    public ResponseEntity<String> deleteMedia(@PathVariable("id") int id) {
        mediaService.deleteMediaById(id);
        return new ResponseEntity<>("Media deleted!", HttpStatus.OK);
    }



    @GetMapping("getallmediadto")
    public ResponseEntity<List<MediaDTO>> getAllMediaDTO() {
        return ResponseEntity.ok(mediaService.getAllMediaDTO());

    @GetMapping("getmediatype/{mediatype}")
    public ResponseEntity<List<Media>> getMediaByType(@PathVariable String mediatype) {
        List<Media> mediaList = mediaService.getMediaByType(mediatype);
        return ResponseEntity.ok(mediaList);

    }

    @GetMapping("{mediaId}")
    public ResponseEntity<Media> getMediaByType(@PathVariable int mediaId) {
        Optional<Media> media = mediaService.getMediaById(mediaId);
        return ResponseEntity.ok(media.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }
}
