package com.example.raphael_media.controllers;

import com.example.raphael_media.entities.*;
import com.example.raphael_media.services.ArtistService;
import com.example.raphael_media.services.MusicService;
import jakarta.ws.rs.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ArtistController {

    @Autowired
    private ArtistService artistService;

    @GetMapping("/api/media/{artistId}/albums")
    public ResponseEntity<List<Album>> getAllAlbumsByArtist(@PathVariable int artistId) {
        List<Album> albums = artistService.getAllAlbumsByArtistId(artistId);
        return ResponseEntity.ok(albums);
    }

    @GetMapping("/api/media/{artistId}/mediaList")
    public ResponseEntity<List<Media>> getAllMediaByArtist(@PathVariable int artistId) {
        List<Media> mediaList = artistService.getAllMediaByArtistId(artistId);
        return ResponseEntity.ok(mediaList);
    }

}
