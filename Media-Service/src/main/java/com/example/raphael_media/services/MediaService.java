package com.example.raphael_media.services;

import com.example.raphael_media.entities.Media;
import com.example.raphael_media.repositores.MediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MediaService implements MediaServiceInterface {


    @Autowired
    MediaRepository mediaRepository;

    @Override
    public Media getMediaById(int mediaId) {
        return null;
    }

    @Override
    public List<Media> getAllMedia() {
        return mediaRepository.findAll();
    }

    @Override
    public void addNewMedia(Media media) {
        mediaRepository.save(media);
    }

    @Override
    public Media updateMedia(int mediaId, Media media) {
        return null;
    }

    @Override
    public void deleteMediaById(int mediaId) {

    }
}
