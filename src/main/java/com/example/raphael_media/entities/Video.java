package com.example.raphael_media.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 30)
    private String title;
    @Column(length = 50)
    private String URL;

    @Column(length = 15)
    private LocalDate releaseDate;

    @ManyToMany
    @JoinTable(
            name = "video_artist",
            joinColumns = @JoinColumn(name = "video_id" ),
            inverseJoinColumns = @JoinColumn(name = "artist_id" ))
    @JsonIgnore
    private List<Artist> listOfArtists;

    @ManyToMany
    @JoinTable(
            name = "video_albums",
            joinColumns = @JoinColumn(name = "video_id" ),
            inverseJoinColumns = @JoinColumn(name = "album_id" ))
    @JsonIgnore
    private List<Album> listOfAlbums;

    @Column(name = "List_Of_Genre",length = 100)
    @OneToMany (mappedBy = "genre",cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Genre> listOfGenre;

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

    public List<Artist> getListOfArtists() {
        return listOfArtists;
    }

    public void setListOfArtists(List<Artist> listOfArtists) {
        this.listOfArtists = listOfArtists;
    }

    public List<Album> getListOfAlbums() {
        return listOfAlbums;
    }

    public void setListOfAlbums(List<Album> listOfAlbums) {
        this.listOfAlbums = listOfAlbums;
    }

    public List<Genre> getListOfGenre() {
        return listOfGenre;
    }

    public void setListOfGenre(List<Genre> listOfGenre) {
        this.listOfGenre = listOfGenre;
    }
}
