package com.example.raphael_media.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;


@Entity
public class Video extends Media {
    public Video() {
        setMediaType("video");
    }
}
