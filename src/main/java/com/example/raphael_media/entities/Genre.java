package com.example.raphael_media.entities;

import jakarta.persistence.*;

@Entity
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "Genre_namen", length = 50, nullable = false)
    private String genre;

    @ManyToOne
    @JoinColumn(name = "music_id", nullable = false)
    private Music music;

    @ManyToOne
    @JoinColumn(name = "podcast_id", nullable = false)
    private Podcast podcast;

    @ManyToOne
    @JoinColumn(name = "video_id", nullable = false)
    private Podcast video;


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
