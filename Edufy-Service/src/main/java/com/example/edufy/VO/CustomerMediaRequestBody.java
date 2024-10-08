package com.example.edufy.VO;

public class CustomerMediaRequestBody {
    private int customerId;
    private int mediaId;
    private String likeStatus;

    public CustomerMediaRequestBody() {
    }

    public CustomerMediaRequestBody(int customerId, int mediaId, String likeStatus) {
        this.customerId = customerId;
        this.mediaId = mediaId;
        this.likeStatus = likeStatus;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
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
}
