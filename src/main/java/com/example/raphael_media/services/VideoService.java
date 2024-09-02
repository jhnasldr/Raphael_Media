package com.example.raphael_media.services;
//
import com.example.raphael_media.repositores.MusicRepository;
import com.example.raphael_media.repositores.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VideoService implements VideoServiceInterface {

    @Autowired
    private VideoRepository videoRepository;
}
