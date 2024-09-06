package com.example.raphael_media.controllers;

import com.example.raphael_media.entities.*;
import com.example.raphael_media.services.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/api/media/")
public class ArtistController {

    @Autowired
    private ArtistService artistService;

    @GetMapping("{artistId}/albums")
    public ResponseEntity<List<Album>> getAllAlbumsByArtist(@PathVariable int artistId) {
        List<Album> albums = artistService.getAllAlbumsByArtistId(artistId);
        return ResponseEntity.ok(albums);
    }

    @GetMapping("{artistId}/medialist")
    public ResponseEntity<List<Media>> getAllMediaByArtist(@PathVariable int artistId) {
        List<Media> mediaList = artistService.getAllMediaByArtistId(artistId);
        return ResponseEntity.ok(mediaList);
    }




}
