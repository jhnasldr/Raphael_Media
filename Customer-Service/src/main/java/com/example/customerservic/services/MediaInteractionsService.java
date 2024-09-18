package com.example.customerservic.services;

import com.example.customerservic.entities.MediaInteractions;
import com.example.customerservic.repositories.MediaInteractionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

@Service
public class MediaInteractionsService implements MediaInteractionsInterface {
    Logger logger = Logger.getLogger(MediaInteractionsService.class);

    @Autowired
    private MediaInteractionsRepository mediaInteractionsRepository;

    @Override
    public MediaInteractions addMediaInteraction(MediaInteractions mediaInteractions) {
        mediaInteractionsRepository.save(mediaInteractions);
        logger.log(Level.WARN, "New media interaction created");
        return mediaInteractions;
    }
}
