package com.example.customerservic.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
public class MediaInteractions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int mediaInteractionId;

    @Column(name = "media_id", length = 20)
    private int mediaId;


    @Column(name = "like_status", length = 20)
    private String likeStatus; //like, dislike or empty


    @Column(name = "times_listened_to", length = 20)
    private int timesListenedTo;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "customer_id")
    //@JsonIgnoreProperties("mediaInteractions")
    private Customer customer;

    public MediaInteractions() {
    }


    public String getLikeStatus() {
        return likeStatus;
    }

    public void setLikeStatus(String likeStatus) {
        this.likeStatus = likeStatus;
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

    public int getMediaId() {
        return mediaId;
    }

    public void setMediaId(int mediaId) {
        this.mediaId = mediaId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
