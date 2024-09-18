package com.example.raphael_media.entities;

import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
public class Music extends Media{
    public Music(String title, String url, LocalDate releaseDate) {
        setTitle(title);
        setURL(url);
        setReleaseDate(releaseDate);
        setMediaType("music");
    }

    public Music() {
        super("Music");
    }
}
