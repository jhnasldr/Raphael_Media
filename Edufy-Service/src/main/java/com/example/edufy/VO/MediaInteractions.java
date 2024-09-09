package com.example.edufy.VO;

public class MediaInteractions {

    private int mediaInteractionId;

    private int mediaId;

    private String likeStatus;


    private int timesListenedTo;

//    private Customer customer;

    public MediaInteractions() {
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

//    public Customer getCustomer() {
//        return customer;
//    }
//
//    public void setCustomer(Customer customer) {
//        this.customer = customer;
//    }

}
