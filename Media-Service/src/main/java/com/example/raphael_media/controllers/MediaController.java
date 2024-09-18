package com.example.raphael_media.controllers;

import com.example.raphael_media.DTOs.MediaDTO;
import com.example.raphael_media.entities.Media;
import com.example.raphael_media.services.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("hasRole('admin')")
    @PostMapping("addnewmedia")
    ResponseEntity<String> addNewMedia(@RequestBody Media media) {
        mediaService.addNewMedia(media);
        return ResponseEntity.ok("New " + media.getMediaType() + " created");
    }

    @PreAuthorize("hasRole('admin')")
    @PutMapping("updatemedia/{id}")
    public ResponseEntity<String> updateMedia(@PathVariable int id, @RequestBody Media media) {
        mediaService.updateMedia(id, media);
        return new ResponseEntity<>(media.getMediaType() + " is updated", HttpStatus.OK);
    }

    @PreAuthorize("hasRole('admin')")
    @DeleteMapping("deletemedia/{id}")
    public ResponseEntity<String> deleteMedia(@PathVariable("id") int id) {
        mediaService.deleteMediaById(id);
        return new ResponseEntity<>("Media deleted!", HttpStatus.OK);
    }

    @GetMapping("getallmediadto")
    public ResponseEntity<List<MediaDTO>> getAllMediaDTO() {
        return ResponseEntity.ok(mediaService.getAllMediaDTO());
    }

    @PostMapping("getlistofmediadtofromlistofid")
    public ResponseEntity<List<MediaDTO>> getMediaDTOsFromListOfIds(@RequestBody List<Integer> mediaId) {
        return ResponseEntity.ok(mediaService.getListOfMediaDTOFromListOfIds(mediaId));
    }

    @GetMapping("getmediatype/{mediatype}")
    public ResponseEntity<List<Media>> getMediaByType(@PathVariable String mediatype) {
        List<Media> mediaList = mediaService.getMediaByType(mediatype);
        return ResponseEntity.ok(mediaList);
    }

    @GetMapping("{mediaId}")
    public ResponseEntity<Media> getMediaById(@PathVariable int mediaId) {
        Optional<Media> media = (mediaService.getMediaById(mediaId));
        return ResponseEntity.ok(media.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }
}
