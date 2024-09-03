package com.example.raphael_media.entities;

import jakarta.persistence.*;

@Entity
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "genre_name", length = 50, nullable = false)
    private String genre;

    @ManyToOne
    @JoinColumn(name = "media_id", nullable = false)
    private Media media;


    public Genre(String genre) {
        this.genre = genre;
    }

    public Genre() {

    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
