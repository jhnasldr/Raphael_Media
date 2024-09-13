package com.example.customerservic.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customerId;

    @Column(name = "user_name",length = 50, nullable = false)
    private String userName;

    @Column(name = "email_adress", length = 75, nullable = false)
    private String emailAdress;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER, mappedBy = "customer")
    @JsonManagedReference
    //@JsonIgnoreProperties("Customer")
    private List<MediaInteractions> mediaInteractions;

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
        return mediaInteractions;
    }

    public void setMediaInteractions(List<MediaInteractions> mediaInteractions) {
        this.mediaInteractions = mediaInteractions;
    }
}
