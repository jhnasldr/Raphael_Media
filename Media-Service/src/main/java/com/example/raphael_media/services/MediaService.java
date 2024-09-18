package com.example.raphael_media.services;

import com.example.raphael_media.DTOs.MediaDTO;
import com.example.raphael_media.entities.Media;
import com.example.raphael_media.entities.Music;
import com.example.raphael_media.entities.Podcast;
import com.example.raphael_media.entities.Video;
import com.example.raphael_media.exceptions.ResourceNotFoundException;
import com.example.raphael_media.repositores.MediaRepository;
import com.example.raphael_media.repositores.MusicRepository;
import com.example.raphael_media.repositores.PodcastRepository;
import com.example.raphael_media.repositores.VideoRepository;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MediaService implements MediaServiceInterface {


    @Autowired
    MediaRepository mediaRepository;

    @Autowired
    MusicRepository musicRepository;

    @Autowired
    VideoRepository videoRepository;

    @Autowired
    PodcastRepository podcastRepository;

    Logger logger = Logger.getLogger(MediaService.class);

    @Override
    public Optional<Media> getMediaById(int mediaId) {
        return mediaRepository.findById(mediaId);
    }

    @Override
    public List<Media> getAllMedia() {
        return mediaRepository.findAll();
    }


    @Override
    public void addNewMedia(Media media) {
        if (media.getMediaType().equalsIgnoreCase("video")) {
            Video video = (Video) media;
            videoRepository.save(video);
        } else if (media.getMediaType().equalsIgnoreCase("music")) {
            Music music = (Music) media;
            musicRepository.save(music);
        } else if (media.getMediaType().equalsIgnoreCase("podcast")) {
            Podcast podcast = (Podcast) media;
            podcastRepository.save(podcast);
        } else {
            throw new IllegalArgumentException("Unknown media type: " + media.getMediaType());
        }

        logger.log(Level.WARN, "New " + media.getMediaType() + " with id: " + media.getId() + " created");
    }


    @Override
    public Media updateMedia(int mediaId, Media media) {
        Media existningMedia;
        if (media.getMediaType().equalsIgnoreCase("video")) {
            existningMedia = videoRepository.findById(mediaId).orElseThrow(() -> new ResourceNotFoundException(media.getMediaType(), "id", mediaId));
        } else if (media.getMediaType().equalsIgnoreCase("music")) {
            existningMedia = musicRepository.findById(mediaId).orElseThrow(() -> new ResourceNotFoundException(media.getMediaType(), "id", mediaId));
        } else if (media.getMediaType().equalsIgnoreCase("podcast")) {
            existningMedia = podcastRepository.findById(mediaId).orElseThrow(() -> new ResourceNotFoundException(media.getMediaType(), "id", mediaId));
        } else {
            throw new IllegalArgumentException("Unknown media type: " + media.getMediaType());
        }

        if (media.getMediaType() != null) {
            existningMedia.setMediaType(media.getMediaType());
        }
        if (media.getAlbums() != null) {
            existningMedia.setAlbums(media.getAlbums());
        }
        if (media.getArtists() != null) {
            existningMedia.setArtists(media.getArtists());
        }
        if (media.getGenres() != null) {
            existningMedia.setGenres(media.getGenres());
        }
        if (media.getReleaseDate() != null) {
            existningMedia.setReleaseDate(media.getReleaseDate());
        }
        if (media.getTitle() != null) {
            existningMedia.setTitle(media.getTitle());
        }

        if (media.getURL() != null) {
            existningMedia.setURL(media.getURL());
        }

        if (media.getMediaType().equalsIgnoreCase("video")) {
            Video video = (Video) existningMedia;
            videoRepository.save(video);
        } else if (media.getMediaType().equalsIgnoreCase("music")) {
            Music music = (Music) existningMedia;
            musicRepository.save(music);
        } else if (media.getMediaType().equalsIgnoreCase("podcast")) {
            Podcast podcast = (Podcast) existningMedia;
            podcastRepository.save(podcast);
        }

        logger.log(Level.WARN, "Media with id: " + mediaId + " updated");
        return existningMedia;
    }

    @Override
    public void deleteMediaById(int mediaId) {
        if (!mediaRepository.existsById(mediaId)) {
            throw new ResourceNotFoundException("Media", "id", mediaId);
        }
        mediaRepository.deleteById(mediaId);
        logger.log(Level.WARN, "Media with id: " + mediaId + " deleted");
    }

    @Override
    public List<MediaDTO> getAllMediaDTO() {
        List<Media> allMedia = mediaRepository.findAll();
        return allMedia.stream().map(this::convertToMediaDTO).toList();
    }

    @Override
    public List<MediaDTO> getListOfMediaDTOFromListOfIds(List<Integer> listOfId) {
        List<Media> listOfMediaFromIds = mediaRepository.findAllById(listOfId);
        Map<Integer, Media> mediaMap = listOfMediaFromIds.stream().collect(Collectors.toMap(Media::getId, media -> media));
        List<MediaDTO> orderedMediaDTOs = listOfId.stream().map(mediaMap::get).map(this::convertToMediaDTO).toList();
        return orderedMediaDTOs;
    }

    private MediaDTO convertToMediaDTO(Media media) {
        return new MediaDTO(media.getId(), media.getTitle(), media.getGenres(), media.getArtists(), media.getMediaType());
    }


    public List<Media> getMediaByType(String mediaType) {
        List<Media> mediaList = mediaRepository.findByMediaType(mediaType);
        if (mediaList.isEmpty()) {
            throw new ResourceNotFoundException("Media", "type", mediaType);
        }
        return mediaList;
    }

}
