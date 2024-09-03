package com.example.raphael_media.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Media {
    @ManyToMany
    @JoinTable(
            name = "media_artists",
            joinColumns = @JoinColumn(name = "media_id" ),
            inverseJoinColumns = @JoinColumn(name = "artist_id" ))
    @JsonIgnore
    private List<Artist> artists;

    @ManyToMany
    @JoinTable(
            name = "media_albums",
            joinColumns = @JoinColumn(name = "media_id" ),
            inverseJoinColumns = @JoinColumn(name = "album_id" ))
    @JsonIgnore
    private List<Album> albums;

    @Column(name = "List_Of_Genre",length = 100)
    @OneToMany (mappedBy = "genre",cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Genre> listOfGenre;

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 30, nullable = false)
    private String mediaType;
    @Column(length = 30, nullable = false)
    private String title;
    @Column(length = 50, nullable = false)
    private String URL;
    @Column(length = 15,nullable = false)
    private LocalDate releaseDate;

    public Media() {
    }

    public Long getId() {
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
