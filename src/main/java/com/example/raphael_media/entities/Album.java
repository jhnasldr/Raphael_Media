package com.example.raphael_media.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int albumId;



    @Column(name = "album_name", length = 100, nullable = false)
    private String albumName;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "artist_id", nullable = false)
    private Artist artist;
    @ManyToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER, mappedBy = "albums")
    @JsonIgnore
    private List<Media> mediaList;
/*
    @ManyToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER, mappedBy = "listOfAlbums")
    @JsonIgnore
    private List<Music> musicList;

    @ManyToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER, mappedBy = "listOfAlbums")
    @JsonIgnore
    private List<Video> videoList;

    @ManyToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER, mappedBy = "listOfAlbums")
    @JsonIgnore
    private List<Podcast> podcastList;


 */
    public Album() {

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
/*
    public List<Music> getMusicList() {
        return musicList;
    }

    public void setMusicList(List<Music> musicsList) {
        this.musicList = musicsList;
    }

    public List<Video> getVideoList() {
        return videoList;
    }

    public void setVideoList(List<Video> videoList) {
        this.videoList = videoList;
    }

    public List<Podcast> getPodcastList() {
        return podcastList;
    }

    public void setPodcastList(List<Podcast> podcastList) {
        this.podcastList = podcastList;
    }

 */

}
