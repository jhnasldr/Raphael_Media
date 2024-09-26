package com.example.raphael_media.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int genreId;

    @Column(name = "genre_name", length = 50, nullable = false)
    private String genre;

    @ManyToMany(mappedBy = "genres")
    @JsonIgnore
    private List<Media> mediaList;

    public Genre() {
    }

    public Genre(String genre, List<Media> mediaList) {
        this.genre = genre;
        this.mediaList = mediaList;
    }

    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }

    public Genre(String genre) {
        this.genre = genre;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public List<Media> getMediaList() {
        return mediaList;
    }

    public void setMediaList(List<Media> mediaList) {
        this.mediaList = mediaList;
    }

}