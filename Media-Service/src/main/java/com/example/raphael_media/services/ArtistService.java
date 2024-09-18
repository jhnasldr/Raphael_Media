package com.example.raphael_media.services;

import com.example.raphael_media.entities.Album;
import com.example.raphael_media.entities.Artist;
import com.example.raphael_media.entities.Media;
import com.example.raphael_media.exceptions.ResourceNotFoundException;

import com.example.raphael_media.repositores.ArtistRepository;
import com.example.raphael_media.repositores.MediaRepository;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistService implements ArtistServiceInterface {

    @Autowired
    public ArtistRepository artistRepository;

    @Autowired
    private MediaRepository mediaRepository;


    Logger logger = Logger.getLogger(ArtistService.class);

    @Override
    public List<Album> getAllAlbumsByArtistId(int artistId) {
        Artist artist = artistRepository.findById(artistId)
                .orElseThrow(() -> new RuntimeException("Artist with id " + artistId + " not found"));
        return artist.getAlbums();
    }

    @Override
    public List<Media> getAllMediaByArtistId(int artistId) {
        Artist artist = artistRepository.findById(artistId)
                .orElseThrow(() -> new RuntimeException("Artist with id " + artistId + " not found"));
        return artist.getMediaList();
    }

    @Override
    public Artist addArtist(Artist artist) {
        artistRepository.save(artist);
        logger.log(Level.WARN, "New artist with id: " + artist.getArtistId() + " created");
        return artist;
    }

    @Override
    public List<Artist> fetchAllArtist() {
        return artistRepository.findAll();
    }

    @Override
    public Artist fetchArtist(int artistId) {
        return artistRepository.findById(artistId).orElseThrow(() -> new ResourceNotFoundException("artist", "id", artistId));
    }

    @Override
    public Artist updateArtist(int artistId, Artist artist) {
        Artist existingArtist = artistRepository.findById(artistId).orElseThrow(() -> new ResourceNotFoundException("artist", "id", artistId));
        if (artist.getArtistName() != null) {
            existingArtist.setArtistName(artist.getArtistName());
        }

        if (artist.getMediaList() != null) {
            existingArtist.setMediaList(artist.getMediaList());
        }

        if (artist.getAlbums() != null) {
            existingArtist.setAlbums(artist.getAlbums());
        }
        artistRepository.save(existingArtist);
        logger.log(Level.WARN, "Artist with id: " + artistId + " updated");
        return existingArtist;
    }

    @Override
    public void deleteArtist(int artistId) {
        Artist artist = artistRepository.findById(artistId).orElseThrow(() -> new ResourceNotFoundException("Artist", "id", artistId));

        for (Media media : artist.getMediaList()) {
            media.getArtists().remove(artist);
            mediaRepository.save(media);
        }

        artistRepository.delete(artist);
        logger.log(Level.WARN, "Artist with id: " + artistId + " deleted");
    }

    public void setMediaRepository(MediaRepository mediaRepository) {
        this.mediaRepository = mediaRepository;
    }
}
