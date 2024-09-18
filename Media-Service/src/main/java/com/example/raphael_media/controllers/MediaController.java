package com.example.raphael_media.controllers;

import com.example.raphael_media.DTOs.MediaDTO;
import com.example.raphael_media.entities.Media;
import com.example.raphael_media.entities.Music;
import com.example.raphael_media.entities.Podcast;
import com.example.raphael_media.entities.Video;
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
    @PostMapping("addnewmusic")
    ResponseEntity<String> addNewMusic(@RequestBody Music music) {
        mediaService.addNewMusic(music);
        return ResponseEntity.ok("New music created");
    }
    @PreAuthorize("hasRole('admin')")
    @PostMapping("addnewpodcast")
    ResponseEntity<String> addNewPodcast(@RequestBody Podcast podcast) {
        mediaService.addNewPodcast(podcast);
        return ResponseEntity.ok("New podcast created");
    }
    @PreAuthorize("hasRole('admin')")
    @PostMapping("addnewvideo")
    ResponseEntity<String> addNewVideo(@RequestBody Video video) {
        mediaService.addNewVideo(video);
        return ResponseEntity.ok("New video created");
    }

    @PreAuthorize("hasRole('admin')")
    @PutMapping("updatevideo/{id}")
    public ResponseEntity<String> updateVideo(@PathVariable int id, @RequestBody Video video) {
        mediaService.updateVideo(id, video);
        return new ResponseEntity<>("Video is updated", HttpStatus.OK);
    }
    @PreAuthorize("hasRole('admin')")
    @PutMapping("updatemusic/{id}")
    public ResponseEntity<String> updateMusic(@PathVariable int id, @RequestBody Music music) {
        mediaService.updateMusic(id, music);
        return new ResponseEntity<>("Music is updated", HttpStatus.OK);
    }
    @PreAuthorize("hasRole('admin')")
    @PutMapping("updatepodcast/{id}")
    public ResponseEntity<String> updatePodcast(@PathVariable int id, @RequestBody Podcast podcast) {
        mediaService.updatePodcast(id, podcast);
        return new ResponseEntity<>("Podcast is updated", HttpStatus.OK);
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


    @PostMapping ("getlistofmediadtofromlistofid")
    public ResponseEntity<List<MediaDTO>> getMediaDTOsFromListOfIds(@RequestBody List<Integer> mediaId) {
        return ResponseEntity.ok(mediaService.getListOfMediaDTOFromListOfIds(mediaId));
    }
    @PreAuthorize("hasRole('user')")
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
