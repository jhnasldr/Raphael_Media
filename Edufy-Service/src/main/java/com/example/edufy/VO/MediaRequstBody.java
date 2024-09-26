package com.example.edufy.VO;

public class MediaRequstBody {
    private Integer customerId;
    private Integer mediaId;

    public MediaRequstBody() {
    }

    public MediaRequstBody(Integer customerId, Integer mediaId) {
        this.customerId = customerId;
        this.mediaId = mediaId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getMediaId() {
        return mediaId;
    }

    public void setMediaId(Integer mediaId) {
        this.mediaId = mediaId;
    }
}
