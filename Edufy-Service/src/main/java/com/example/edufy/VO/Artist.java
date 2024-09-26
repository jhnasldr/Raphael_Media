package com.example.edufy.VO;

import jakarta.persistence.*;

import java.util.List;

public class Artist {
    private int artistId;

    private String artistName;
    private List<Album> albums;

    private List<Media> mediaList;

    public Artist() {
    }

    public Artist(int artistId, String artistName, List<Album> albums, List<Media> mediaList) {
        this.artistId = artistId;
        this.artistName = artistName;
        this.albums = albums;
        this.mediaList = mediaList;
    }

    public int getArtistId() {
        return artistId;
    }

    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }

    public List<Media> getMediaList() {
        return mediaList;
    }

    public void setMediaList(List<Media> mediaList) {
        this.mediaList = mediaList;
    }
}
