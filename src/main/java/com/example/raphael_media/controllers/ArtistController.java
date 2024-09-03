package com.example.raphael_media.controllers;

import com.example.raphael_media.entities.Album;
import com.example.raphael_media.entities.Music;
import com.example.raphael_media.services.ArtistService;
import com.example.raphael_media.services.MusicService;
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
    @Autowired
    private MusicService musicService;

    @GetMapping("/api/{artistId}/albums")
    public ResponseEntity<List<Album>> getAllAlbumsByArtist(@PathVariable int artistId) {
        List<Album> albums = artistService.getAllAlbumsByArtistId(artistId);
        return ResponseEntity.ok(albums);

    }

}
