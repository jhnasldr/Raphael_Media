package com.example.customerservic.services;

import com.example.customerservic.entities.MediaInteractions;
import com.example.customerservic.repositories.MediaInteractionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MediaInteractionsService implements MediaInteractionsInterface{

    @Autowired
    private MediaInteractionsRepository mediaInteractionsRepository;

    @Override
    public MediaInteractions addMediaInteraction(MediaInteractions mediaInteractions) {
        mediaInteractionsRepository.save(mediaInteractions);
        return mediaInteractions;
    }
}
