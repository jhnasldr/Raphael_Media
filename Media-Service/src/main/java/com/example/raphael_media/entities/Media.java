package com.example.raphael_media.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Media {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 30, nullable = false)
    private String mediaType;
    @Column(length = 60, nullable = false)
    private String title;
    @Column(length = 75, nullable = false)
    private String URL;
    @Column(length = 15,nullable = false)
    private LocalDate releaseDate;

    @ManyToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    @JoinTable(
            name = "media_artists",
            joinColumns = @JoinColumn(name = "media_id" ),
            inverseJoinColumns = @JoinColumn(name = "artist_id" ))
    @JsonIgnore
    private List<Artist> artists;

    @ManyToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    @JoinTable(
            name = "media_albums",
            joinColumns = @JoinColumn(name = "media_id" ),
            inverseJoinColumns = @JoinColumn(name = "album_id" ))
    @JsonIgnore
    private List<Album> albums;

    @ManyToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    @JoinTable(
            name = "media_genres",
            joinColumns = @JoinColumn(name = "media_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    @JsonIgnore
    private List<Genre> genres;

    public Media() {
    }
    public Media(String mediaType, String title, String URL) {
        this.mediaType = mediaType;
        this.title = title;
        this.URL = URL;
    }
    public Media(String mediaType, String title, String URL, LocalDate releaseDate, List<Artist> artists, List<Album> albums, List<Genre> genres) {

        this.mediaType = mediaType;
        this.title = title;
        this.URL = URL;
        this.releaseDate = releaseDate;
        this.artists = artists;
        this.albums = albums;
        this.genres = genres;
    }

    public Media(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public List<Artist> getArtists() {
        return artists;
    }

    public void setArtists(List<Artist> artists) {
        this.artists = artists;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
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
}
