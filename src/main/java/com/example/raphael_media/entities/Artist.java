package com.example.raphael_media.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int artistId;

    @Column(name = "artist_name",length = 50, nullable = false)
    private String artistName;

    /*@OneToMany(mappedBy = "artist")
    private List<Album> albums;*/

    @ManyToMany(cascade = CascadeType.MERGE, mappedBy = "artist")
    private List<Music> musicsList;

   /* @ManyToMany(cascade = CascadeType.MERGE, mappedBy = "artist")
    @JoinTable (
            name = "artist_video",
            joinColumns = @JoinColumn(name = "artist_id"),
            inverseJoinColumns = @JoinColumn(name = "video_id")
    )
    private List<Video> videoList;*/

    @ManyToMany(cascade = CascadeType.MERGE, mappedBy = "artist")
    private List<Podcast> podcastList;

    public Artist() {

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

   /* public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }*/

    public List<Music> getMusicsList() {
        return musicsList;
    }

    public void setMusicsList(List<Music> musicsList) {
        this.musicsList = musicsList;
    }

   /* public List<Video> getVideoList() {
        return videoList;
    }

    public void setVideoList(List<Video> videoList) {
        this.videoList = videoList;
    }*/

    public List<Podcast> getPodcastList() {
        return podcastList;
    }

    public void setPodcastList(List<Podcast> podcastList) {
        this.podcastList = podcastList;
    }
}
