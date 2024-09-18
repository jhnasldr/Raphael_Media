package com.example.raphael_media.controllers;

import com.example.raphael_media.entities.*;
import com.example.raphael_media.services.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/media/")
public class ArtistController {

    @Autowired
    private ArtistService artistService;

    @PreAuthorize("hasRole('user')")
    @GetMapping("{artistId}/albums")
    public ResponseEntity<List<Album>> getAllAlbumsByArtist(@PathVariable int artistId) {
        List<Album> albums = artistService.getAllAlbumsByArtistId(artistId);
        return ResponseEntity.ok(albums);
    }

    @PreAuthorize("hasRole('user')")
    @GetMapping("{artistId}/medialist")
    public ResponseEntity<List<Media>> getAllMediaByArtist(@PathVariable int artistId) {
        List<Media> mediaList = artistService.getAllMediaByArtistId(artistId);
        return ResponseEntity.ok(mediaList);
    }

    @PreAuthorize("hasRole('user')")
    @GetMapping("artist/{artistId}")
    public ResponseEntity<Artist> getArtistById(@PathVariable int artistId) {
        return new ResponseEntity<>(artistService.fetchArtist(artistId), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('user')")
    @GetMapping("getallartist")
    public ResponseEntity<List<Artist>> getAllArtist() {
        return ResponseEntity.ok(artistService.fetchAllArtist());
    }

    @PreAuthorize("hasRole('admin')")
    @PostMapping("addartist")
    public ResponseEntity<Artist> addArtist(@RequestBody Artist artist) {
        return new ResponseEntity<>(artistService.addArtist(artist), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('admin')")
    @PutMapping("updateartist/{artistId}")
    public ResponseEntity<Artist> updateArtist(@PathVariable int artistId, @RequestBody Artist artist) {
        return new ResponseEntity<>(artistService.updateArtist(artistId, artist), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('admin')")
    @DeleteMapping("deleteartist/{artistId}")
    public ResponseEntity<String> deleteArtist(@PathVariable int artistId) {
        artistService.deleteArtist(artistId);
        return new ResponseEntity<>("Artist is deleted", HttpStatus.OK);
    }


}
