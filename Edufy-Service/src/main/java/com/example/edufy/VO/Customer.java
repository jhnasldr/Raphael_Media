package com.example.edufy.VO;

import java.util.List;

public class Customer {

    //Denna klass ska vara likadan som vår Customer entitet i Customer mikrotjänsten men den ska inte vara en entitet

    private int customerId;

    private String userName;

    private String emailAdress;

    private List<MediaInteractions> MediaInteractions;

    public Customer() {
    }


    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmailAdress() {
        return emailAdress;
    }

    public void setEmailAdress(String emailAdress) {
        this.emailAdress = emailAdress;
    }

    public List<MediaInteractions> getMediaInteractions() {
        return MediaInteractions;
    }

    public void setMediaInteractions(List<MediaInteractions> mediaInteractions) {
        MediaInteractions = mediaInteractions;
    }
}
