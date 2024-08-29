package com.example.raphael_media.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Podcast implements Media{
    @Id
    @GeneratedValue
    private Integer id;

    @Override
    public String title() {
        return null;
    }

    @Override
    public String URL() {
        return null;
    }

    @Override
    public LocalDateTime releaseDate() {
        return null;
    }

    @Override
    public List<Artist> listOfArtists() {
        return null;
    }

    @Override
    public List<Album> listOfAlbums() {
        return null;
    }

    @Override
    public List<Genre> listOfGenres() {
        return null;
    }
}
