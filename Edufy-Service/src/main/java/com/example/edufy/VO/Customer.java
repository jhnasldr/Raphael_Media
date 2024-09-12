package com.example.edufy.VO;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;

import java.util.List;

public class Customer {

    //Denna klass ska vara likadan som vår Customer entitet i Customer mikrotjänsten men den ska inte vara en entitet

    private int customerId;

    private String userName;

    private String emailAdress;

    @OneToMany(cascade = CascadeType.MERGE,fetch = FetchType.EAGER, mappedBy = "customer")
    @JsonManagedReference
    private List<MediaInteractions> MediaInteractions;

    public Customer() {
    }

    public Customer(int customerId, String userName, String emailAdress, List<MediaInteractions> mediaInteractions) {
        this.customerId = customerId;
        this.userName = userName;
        this.emailAdress = emailAdress;
        MediaInteractions = mediaInteractions;
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
