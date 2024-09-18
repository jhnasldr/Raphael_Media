package com.example.raphael_media.entities;

import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
public class Video extends Media {
    public Video(String title, String url, LocalDate releaseDate) {
        setTitle(title);
        setURL(url);
        setReleaseDate(releaseDate);
        setMediaType("video");
    }

    public Video() {
        super("Video");
    }
}
