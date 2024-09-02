package com.example.raphael_media.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Podcast{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 30, nullable = false)
    private String title;
    @Column(length = 50, nullable = false)
    private String URL;

    @Column(length = 15,nullable = false)
    private LocalDate releaseDate;

    @ManyToMany
    @JoinTable(
            name = "podcast_artist",
            joinColumns = @JoinColumn(name = "podcast_id" ),
            inverseJoinColumns = @JoinColumn(name = "artist_id" ))
    private List<Artist> listOfArtists;

    @ManyToMany
    @JoinTable(
            name = "podcast_albums",
            joinColumns = @JoinColumn(name = "podcast_id" ),
            inverseJoinColumns = @JoinColumn(name = "album_id" ))
    private List<Album> listOfAlbums;

    @Column(name = "List_Of_Genre",length = 100)
    @OneToMany (mappedBy = "genre",cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private List<Genre> listOfGenre;

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
