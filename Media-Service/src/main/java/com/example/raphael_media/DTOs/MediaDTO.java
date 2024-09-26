package com.example.raphael_media.DTOs;

import com.example.raphael_media.entities.Artist;
import com.example.raphael_media.entities.Genre;

import java.util.List;

public class MediaDTO {
    private int id;
    private String title;
    private List<Genre> genres;
    private List<Artist> artists;
    private String mediaType;

    public MediaDTO() {
    }

    public MediaDTO(int id, String title, String mediaType) {
        this.id = id;
        this.title = title;
        this.mediaType = mediaType;
    }

    public MediaDTO(int id, String title, List<Genre> genres, List<Artist> artists, String mediaType) {
        this.id = id;
        this.title = title;
        this.genres = genres;
        this.artists = artists;
        this.mediaType = mediaType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public List<Artist> getArtists() {
        return artists;
    }

    public void setArtists(List<Artist> artists) {
        this.artists = artists;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

}
