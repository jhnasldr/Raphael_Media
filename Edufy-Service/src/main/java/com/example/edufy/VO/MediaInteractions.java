package com.example.edufy.VO;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class MediaInteractions {

    private int mediaInteractionId = 0;

    private int mediaId;

    private String likeStatus;

    private int timesListenedTo;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public void increasePlayCount() {
        this.timesListenedTo++;
    }

    public MediaInteractions() {
    }

    public MediaInteractions(String likeStatus, int timesListenedTo, Customer customer) {
        this.likeStatus = likeStatus;
        this.timesListenedTo = timesListenedTo;
        this.customer = customer;
    }

    public MediaInteractions(int mediaInteractionId, int mediaId, String likeStatus, int timesListenedTo) {
        this.mediaInteractionId = mediaInteractionId;
        this.mediaId = mediaId;
        this.likeStatus = likeStatus;
        this.timesListenedTo = timesListenedTo;
    }

    public MediaInteractions(int mediaInteractionId, int mediaId, String likeStatus, int timesListenedTo, Customer customer) {
        this.mediaInteractionId = mediaInteractionId;
        this.mediaId = mediaId;
        this.likeStatus = likeStatus;
        this.timesListenedTo = timesListenedTo;
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
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

}
