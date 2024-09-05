package com.example.raphael_media.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
public class Podcast extends Media{

    public Podcast() {
        setMediaType("podcast");
    }

}
