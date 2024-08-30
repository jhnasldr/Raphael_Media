package com.example.raphael_media.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Podcast{

    @Id
    @GeneratedValue
    private Integer id;
    @Column(length = 30, nullable = false)
    private String title;
    @Column(length = 50, nullable = false)
    private String URL = "https://rapharlstudio/podcast";

    @Column(length = 15,nullable = false)
    private LocalDate releaseDate;

/*
    @ManyToMany
    @JoinTable(
            name = "podcast_artist",
            joinColumns = @JoinColumn(name = "podcast_id" ),
            inverseJoinColumns = @JoinColumn(name = "artist_id" ))
    private List<Artist> listOfArtists;

        @ManyToMany
    @JoinTable(
            name = "podcast_artist",
            joinColumns = @JoinColumn(name = "podcast_id" ),
            inverseJoinColumns = @JoinColumn(name = "album_id" ))
    private List<Album> listOfArtists;

        @ManyToMany
    @JoinTable(
            name = "podcast_artist",
            joinColumns = @JoinColumn(name = "podcast_id" ),
            inverseJoinColumns = @JoinColumn(name = "genre_id" ))
    private List<Genre> listOfArtists;


 */

    public Podcast(String title) {
        this.title = title;
    }

    public Podcast() {
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
