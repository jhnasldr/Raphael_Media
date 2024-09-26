package com.example.edufy.VO;

import java.util.List;

public class Genre {
    private int genreId;
    private String genre;
    private List<Media> mediaList;

    public Genre() {
    }

    public Genre(int genreId, String genre, List<Media> mediaList) {
        this.genreId = genreId;
        this.genre = genre;
        this.mediaList = mediaList;
    }

    public Genre(int genreId, String genre) {
        this.genreId = genreId;
        this.genre = genre;
    }

    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
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
