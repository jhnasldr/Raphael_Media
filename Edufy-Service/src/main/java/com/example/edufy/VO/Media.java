package com.example.edufy.VO;


import java.time.LocalDate;
import java.util.List;

public class Media {
    private int id;
    private String mediaType;
    private String title;
    private String URL;
    private LocalDate releaseDate;
    private List<Artist> artists;
    private List<Album> albums;
    private List<Genre> genres;

    public Media() {
    }

    public Media(int id, String mediaType, String title, String URL, LocalDate releaseDate) {
        this.id = id;
        this.mediaType = mediaType;
        this.title = title;
        this.URL = URL;
        this.releaseDate = releaseDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
