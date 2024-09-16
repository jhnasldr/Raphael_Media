package com.example.raphael_media.controllers;

import com.example.raphael_media.entities.*;
import com.example.raphael_media.services.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @GetMapping("artist/{artistId}")
    public ResponseEntity<Artist> getArtistById(@PathVariable int artistId){
        return new ResponseEntity<>(artistService.fetchArtist(artistId), HttpStatus.OK);
    }

    @GetMapping("getallartist")
    public ResponseEntity<List<Artist>> getAllArtist(){
        return ResponseEntity.ok(artistService.fetchAllArtist());
    }

    @PostMapping("addartist")
    public ResponseEntity<Artist> addArtist(@RequestBody Artist artist){
        return new ResponseEntity<>(artistService.addArtist(artist),HttpStatus.OK);
    }

    @PutMapping("updateartist/{artistId}")
    public ResponseEntity<Artist> updateArtist(@PathVariable int artistId, @RequestBody Artist artist){
        return new ResponseEntity<>(artistService.updateArtist(artistId,artist),HttpStatus.OK);
    }

    @DeleteMapping("deleteartist/{artistId}")
    public ResponseEntity<String> deleteArtist(@PathVariable int artistId){
        artistService.deleteArtist(artistId);
        return new ResponseEntity<>("Artist is deleted",HttpStatus.OK);
    }


}
