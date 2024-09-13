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
    public void addNewMusic(Music music) {
        musicRepository.save(music);
        logger.log(Level.WARN, "New music with id: " + music.getId() + " created");
    }

    @Override
    public void addNewVideo(Video video) {
        videoRepository.save(video);
        logger.log(Level.WARN, "New video with id: " + video.getId() + " created");
    }

    @Override
    public void addNewPodcast(Podcast podcast) {
        podcastRepository.save(podcast);
        logger.log(Level.WARN, "New podcast with id: " + podcast.getId() + " created");
    }

    @Override
    public Music updateMusic(int musicId, Music music) {
        Music existningMusic = musicRepository.findById(musicId).orElseThrow(() -> new ResourceNotFoundException(music.getMediaType(), "id", musicId));
        if (music.getMediaType() != null) {
            existningMusic.setMediaType(music.getMediaType());
        }
        if (music.getAlbums() != null) {
            existningMusic.setAlbums(music.getAlbums());
        }
        if (music.getArtists() != null) {
            existningMusic.setArtists(music.getArtists());
        }
        if (music.getGenres() != null) {
            existningMusic.setGenres(music.getGenres());
        }
        if (music.getReleaseDate() != null) {
            existningMusic.setReleaseDate(music.getReleaseDate());
        }
        if (music.getTitle() != null) {
            existningMusic.setTitle(music.getTitle());
        }

        if (music.getURL() != null) {
            existningMusic.setURL(music.getURL());
        }

        musicRepository.save(existningMusic);
        logger.log(Level.WARN, "Music with id: " + musicId + " updated");
        return existningMusic;
    }

    @Override
    public Video updateVideo(int videoId, Video video) {
        Video existningVideo = videoRepository.findById(videoId).orElseThrow(() -> new ResourceNotFoundException(video.getMediaType(), "id", videoId));
        if (video.getMediaType() != null) {
            existningVideo.setMediaType(video.getMediaType());
        }
        if (video.getAlbums() != null) {
            existningVideo.setAlbums(video.getAlbums());
        }
        if (video.getArtists() != null) {
            existningVideo.setArtists(video.getArtists());
        }
        if (video.getGenres() != null) {
            existningVideo.setGenres(video.getGenres());
        }
        if (video.getReleaseDate() != null) {
            existningVideo.setReleaseDate(video.getReleaseDate());
        }
        if (video.getTitle() != null) {
            existningVideo.setTitle(video.getTitle());
        }

        if (video.getURL() != null) {
            existningVideo.setURL(video.getURL());
        }

        videoRepository.save(existningVideo);
        logger.log(Level.WARN, "Video with id: " + videoId + " updated");
        return existningVideo;
    }

    @Override
    public Podcast updatePodcast(int podcastId, Podcast podcast) {
        Podcast existningPodcast = podcastRepository.findById(podcastId).orElseThrow(() -> new ResourceNotFoundException(podcast.getMediaType(), "id", podcastId));
        if (podcast.getMediaType() != null) {
            existningPodcast.setMediaType(podcast.getMediaType());
        }
        if (podcast.getAlbums() != null) {
            existningPodcast.setAlbums(podcast.getAlbums());
        }
        if (podcast.getArtists() != null) {
            existningPodcast.setArtists(podcast.getArtists());
        }
        if (podcast.getGenres() != null) {
            existningPodcast.setGenres(podcast.getGenres());
        }
        if (podcast.getReleaseDate() != null) {
            existningPodcast.setReleaseDate(podcast.getReleaseDate());
        }
        if (podcast.getTitle() != null) {
            existningPodcast.setTitle(podcast.getTitle());
        }

        if (podcast.getURL() != null) {
            existningPodcast.setURL(podcast.getURL());
        }

        podcastRepository.save(existningPodcast);

        logger.log(Level.WARN, "Podcast with id: " + podcastId + " updated");
        return existningPodcast;
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

    //
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
