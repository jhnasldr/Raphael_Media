package com.example.raphael_media.services;

import com.example.raphael_media.entities.Media;
import com.example.raphael_media.exceptions.ResourceNotFoundException;
import com.example.raphael_media.repositores.MediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        Media existningMedia = mediaRepository.findById(mediaId).orElseThrow(() -> new ResourceNotFoundException(media.getMediaType(), "id", mediaId));
        if(media.getMediaType() != null){
            existningMedia.setMediaType(media.getMediaType());
        }
        if(media.getAlbums() != null){
            existningMedia.setAlbums(media.getAlbums());
        }
        if(media.getArtists() != null){
            existningMedia.setArtists(media.getArtists());
        }
        if(media.getGenres() != null){
            existningMedia.setGenres(media.getGenres());
        }
        if(media.getReleaseDate() != null){
            existningMedia.setReleaseDate(media.getReleaseDate());
        }
        if(media.getTitle() != null){
            existningMedia.setTitle(media.getTitle());
        }

        if(media.getURL() != null){
            existningMedia.setURL(media.getURL());
        }

        mediaRepository.save(existningMedia);
        return existningMedia;
    }

    @Override
    public void deleteMediaById(int mediaId) {
        if(!mediaRepository.existsById(mediaId)) {
            throw new ResourceNotFoundException("Media", "id", mediaId);
        }
        mediaRepository.deleteById(mediaId);
    }

    @Override
    public List<Media> getMediaByType(String mediaType) {
        List<Media> mediaList = mediaRepository.findByMediaType(mediaType);
        if (mediaList.isEmpty()) {
            throw new ResourceNotFoundException("Media", "type", mediaType);
        }
        return mediaList;
    }
}
