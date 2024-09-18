package com.example.raphael_media.services;

import com.example.raphael_media.DTOs.MediaDTO;
import com.example.raphael_media.entities.Media;
import com.example.raphael_media.entities.Music;
import com.example.raphael_media.entities.Podcast;
import com.example.raphael_media.entities.Video;

import java.util.List;
import java.util.Optional;

public interface MediaServiceInterface {


    Optional<Media> getMediaById(int mediaId);

    List<Media> getAllMedia();

    void addNewMedia(Media media);


    Media updateMedia(int mediaId, Media media);

    void deleteMediaById(int mediaId);
    List<MediaDTO> getAllMediaDTO();

    List<MediaDTO> getListOfMediaDTOFromListOfIds(List<Integer> listOfId);
    List<Media> getMediaByType(String mediaType);


}
