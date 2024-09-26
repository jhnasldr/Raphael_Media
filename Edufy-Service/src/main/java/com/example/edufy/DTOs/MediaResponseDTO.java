package com.example.edufy.DTOs;

public class MediaResponseDTO {
    private int id;
    private String mediaType;
    private String title;
    private String genre;
    private String artist;

    public MediaResponseDTO() {
    }

    public MediaResponseDTO(int id, String mediaType, String title, String genre, String artist) {
        this.id = id;
        this.mediaType = mediaType;
        this.title = title;
        this.genre = genre;
        this.artist = artist;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }
}