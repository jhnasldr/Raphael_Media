package com.example.edufy.VO;

import java.util.List;

public class Album {
    private int albumId;

    private String albumName;

    private Artist artist;

    private List<Media> mediaList;

    public Album() {
    }

    public Album(int albumId, String albumName, Artist artist, List<Media> mediaList) {
        this.albumId = albumId;
        this.albumName = albumName;
        this.artist = artist;
        this.mediaList = mediaList;
    }

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public List<Media> getMediaList() {
        return mediaList;
    }

    public void setMediaList(List<Media> mediaList) {
        this.mediaList = mediaList;
    }
}
