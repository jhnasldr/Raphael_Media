package com.example.raphael_media.services;

import com.example.raphael_media.repositores.MusicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MusicService implements MusicServiceInterface {

    @Autowired
    private MusicRepository musicRepository;
}
