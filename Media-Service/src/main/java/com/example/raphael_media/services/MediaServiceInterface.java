package com.example.raphael_media.services;

import com.example.raphael_media.entities.Media;

import java.util.List;

public interface MediaServiceInterface {

    //
    Media getMediaById(int mediaId);

    List<Media> getAllMedia();

   void addNewMedia(Media media);

    Media updateMedia(int mediaId, Media media);

    void deleteMediaById(int mediaId);

}
