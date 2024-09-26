package com.example.raphael_media.services;

import com.example.raphael_media.DTOs.MediaDTO;
import com.example.raphael_media.entities.*;
import com.example.raphael_media.exceptions.ResourceNotFoundException;
import com.example.raphael_media.repositores.*;
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
    private MediaRepository mediaRepository;

    @Autowired
    private MusicRepository musicRepository;

    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private PodcastRepository podcastRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private ArtistRepository artistRepository;

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
    public void deleteMediaById(int mediaId) { //Todo X
        Media media = mediaRepository.findById(mediaId).orElseThrow(() -> new ResourceNotFoundException("Media", "id", mediaId));

        for (Genre genre : media.getGenres()) {
            genre.getMediaList().remove(media);
            genreRepository.save(genre);
        }
        for (Artist artist : media.getArtists()) {
            artist.getMediaList().remove(media);
            artistRepository.save(artist);
        }
        for (Album album : media.getAlbums()) {
            album.getMediaList().remove(media);
            albumRepository.save(album);
        }

        mediaRepository.delete(media);
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

    public void setMediaRepository(MediaRepository mediaRepository) {
        this.mediaRepository = mediaRepository;
    }

    public void setMusicRepository(MusicRepository musicRepository) {
        this.musicRepository = musicRepository;
    }

    public void setVideoRepository(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    public void setPodcastRepository(PodcastRepository podcastRepository) {
        this.podcastRepository = podcastRepository;
    }

    public void setGenreRepository(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public void setAlbumRepository(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    public void setArtistRepository(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }
}
