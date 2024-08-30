package com.example.raphael_media.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class Video {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(length = 30, nullable = false)
    private String title;
    @Column(length = 50, nullable = false)
    private String URL = "https://rapharlstudio/video";

    @Column(length = 15,nullable = false)
    private LocalDate releaseDate;

/*
    @ManyToMany
    @JoinTable(
            name = "video_artist",
            joinColumns = @JoinColumn(name = "video_id" ),
            inverseJoinColumns = @JoinColumn(name = "artist_id" ))
    private List<Artist> listOfArtists;

    @ManyToMany
    @JoinTable(
            name = "video_artist",
            joinColumns = @JoinColumn(name = "video_id" ),
            inverseJoinColumns = @JoinColumn(name = "album_id" ))
    private List<Album> listOfArtists;

    @ManyToMany
    @JoinTable(
            name = "video_artist",
            joinColumns = @JoinColumn(name = "video_id" ),
            inverseJoinColumns = @JoinColumn(name = "genre_id" ))
    private List<Genre> listOfArtists;


 */

    public Video(String title) {
        this.title = title;
    }

    public Video() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }
/*
    public List<Artist> getListOfArtists() {
        return listOfArtists;
    }

    public void setListOfArtists(List<Genre> listOfArtists) {
        this.listOfArtists = listOfArtists;
    }

    public void setListOfArtists(List<Album> listOfArtists) {
        this.listOfArtists = listOfArtists;
    }

    public void setListOfArtists(List<Artist> listOfArtists) {
        this.listOfArtists = listOfArtists;
    }

 */
}
