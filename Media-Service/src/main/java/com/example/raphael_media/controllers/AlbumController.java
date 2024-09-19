package com.example.raphael_media.controllers;

import com.example.raphael_media.entities.Album;
import com.example.raphael_media.services.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/media/")
public class AlbumController {

    @Autowired
    private AlbumService albumService;

    @PreAuthorize("hasRole('user')")
    @GetMapping("album/{albumId}")
    public ResponseEntity<Album> getAlbum(@PathVariable int albumId) {
        return new ResponseEntity<>(albumService.fetchAlbum(albumId), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('user')")
    @GetMapping("album/getallalbums")
    public ResponseEntity<List<Album>> getAllAlbums() {
        return new ResponseEntity<>(albumService.fetchAllAlbums(), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('admin')")
    @PostMapping("album/addalbum")
    public ResponseEntity<Album> addAlbum(@RequestBody Album album) {
        return new ResponseEntity<>(albumService.addAlbum(album), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('admin')")
    @PutMapping("album/updatealbum/{albumId}") // funkar inte
    public ResponseEntity<Album> updateAlbum(@PathVariable int albumId, @RequestBody Album album) {
        return new ResponseEntity<>(albumService.updateAlbum(albumId, album), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('admin')")
    @DeleteMapping("album/deletealbum/{albumId}")
    public ResponseEntity<String> deleteAlbum(@PathVariable int albumId) {
        albumService.deleteAlbum(albumId);
        return new ResponseEntity<>("Album deleted", HttpStatus.OK);
    }
}
