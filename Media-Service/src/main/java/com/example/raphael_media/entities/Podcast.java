package com.example.raphael_media.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Podcast extends Media{

    public Podcast(String title, String url, LocalDate releaseDate) {
        setTitle(title);
        setURL(url);
        setReleaseDate(releaseDate);
        setMediaType("podcast");
    }

    public Podcast() {
        super("Podcast");
    }
}
