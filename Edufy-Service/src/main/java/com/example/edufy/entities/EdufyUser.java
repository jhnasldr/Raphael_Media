package com.example.edufy.entities;

import jakarta.persistence.*;

@Entity
public class EdufyUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
}
