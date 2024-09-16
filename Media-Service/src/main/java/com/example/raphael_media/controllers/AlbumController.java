package com.example.raphael_media.controllers;

import com.example.raphael_media.entities.Album;
import com.example.raphael_media.services.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/media/")
public class AlbumController {

    @Autowired
    private AlbumService albumService;

    @GetMapping("album/{albumId}")
    public ResponseEntity<Album> getAlbum(@PathVariable int albumId) {
        return new ResponseEntity<>(albumService.fetchAlbum(albumId), HttpStatus.OK);
    }

    @GetMapping("album/getallalbums")
    public ResponseEntity<List<Album>> getAllAlbums() {
        return new ResponseEntity<>(albumService.fetchAllAlbums(), HttpStatus.OK);
    }

    @PostMapping("album/addalbum")
    public ResponseEntity<Album> addAlbum(@RequestBody Album album) {
        return new ResponseEntity<>(albumService.addAlbum(album), HttpStatus.OK);
    }

    @PutMapping("album/updatealbum/{albumId}")
    public ResponseEntity<Album> updateAlbum(@PathVariable int albumId, @RequestBody Album album) {
        return new ResponseEntity<>(albumService.updateAlbum(albumId, album), HttpStatus.OK);
    }

    @DeleteMapping("album/deletealbum/{albumId}")
    public ResponseEntity<String> deleteAlbum(@PathVariable int albumId) {
        albumService.deleteAlbum(albumId);
        return new ResponseEntity<>("Album deleted", HttpStatus.OK);
    }
}
