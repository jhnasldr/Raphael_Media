package com.example.customerservic.entities;

import jakarta.persistence.*;

@Entity
public class MediaInteractions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int mediaInteractionId;

    @Column(name = "music_id", length = 20, nullable = false)
    private int musicId;

    @Column(name = "video_id", length = 20, nullable = false)
    private int videoId;

    @Column(name = "podcast_id")
    private int podcastId;

    @Column(name = "liked", length = 20, nullable = false)
    private String liked;

    @Column(name = "disliked", length = 20, nullable = false)
    private String disliked;

    @Column(name = "times_listened_to", length = 20, nullable = false)
    private int timesListenedTo;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    public MediaInteractions() {
    }


    public String getLiked() {
        return liked;
    }

    public void setLiked(String liked) {
        this.liked = liked;
    }

    public String getDisliked() {
        return disliked;
    }

    public void setDisliked(String disliked) {
        this.disliked = disliked;
    }

    public int getTimesListenedTo() {
        return timesListenedTo;
    }

    public void setTimesListenedTo(int timesListenedTo) {
        this.timesListenedTo = timesListenedTo;
    }

    public int getMediaInteractionId() {
        return mediaInteractionId;
    }

    public void setMediaInteractionId(int mediaInteractionId) {
        this.mediaInteractionId = mediaInteractionId;
    }

    public int getMusicId() {
        return musicId;
    }

    public void setMusicId(int musicId) {
        this.musicId = musicId;
    }

    public int getVideoId() {
        return videoId;
    }

    public void setVideoId(int videoId) {
        this.videoId = videoId;
    }

    public int getPodcastId() {
        return podcastId;
    }

    public void setPodcastId(int podcastId) {
        this.podcastId = podcastId;
    }
}
