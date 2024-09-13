package com.example.raphael_media.controllers;

import com.example.raphael_media.entities.Genre;
import com.example.raphael_media.services.GenreService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/media/")
public class GenreController {

    @Autowired
    private GenreService genreService;

    @GetMapping("genre/{genreid}")
    public ResponseEntity<Genre> getGenreById(@PathVariable int genreid){
        return new ResponseEntity<>(genreService.fetchGenre(genreid), HttpStatus.OK);
    }

    @GetMapping("getallgenre")
    public ResponseEntity<List<Genre>> getAllGenre(){
        return ResponseEntity.ok(genreService.fetchAllGenre());
    }

    @PostMapping("addgenre")
    public ResponseEntity<Genre> addGenre(@RequestBody Genre genre){
        return new ResponseEntity<>(genreService.addGenre(genre),HttpStatus.OK);
    }

    @PutMapping("updategenre/{genreid}")
    public ResponseEntity<Genre> updateGenre(@PathVariable int genreid, @RequestBody Genre genre){
        return new ResponseEntity<>(genreService.updateGenre(genreid,genre),HttpStatus.OK);
    }

    @DeleteMapping("deletegenre/{genreid}")
    public ResponseEntity<String> deleteGenre(@PathVariable int genreid){
        genreService.deleteGenre(genreid);
        return new ResponseEntity<>("Genre is deleted",HttpStatus.OK);
    }
}
