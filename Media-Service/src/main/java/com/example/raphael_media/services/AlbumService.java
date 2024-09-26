package com.example.raphael_media.services;

import com.example.raphael_media.entities.Album;
import com.example.raphael_media.entities.Media;
import com.example.raphael_media.exceptions.ResourceNotFoundException;
import com.example.raphael_media.repositores.AlbumRepository;
import com.example.raphael_media.repositores.ArtistRepository;
import com.example.raphael_media.repositores.MediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.util.List;


@Service
public class AlbumService implements AlbumServiceInterface {
    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private MediaRepository mediaRepository;

    @Autowired
    private ArtistRepository artistRepository;

    Logger logger = Logger.getLogger(AlbumService.class);

    @Override
    public Album addAlbum(Album album) {
        if (album.getArtist() == null || !artistRepository.existsById(album.getArtist().getArtistId())) {
            throw new ResourceNotFoundException("artist", "id", album.getArtist().getArtistId());
        }
        albumRepository.save(album);
        logger.log(Level.WARN, "New album with id: " + album.getAlbumId() + " created");
        return album;
    }

    @Override
    public List<Album> getAllAlbums() {
        return albumRepository.findAll();
    }

    @Override
    public Album getAlbum(int albumId) {
        return albumRepository.findById(albumId).orElseThrow(() -> new ResourceNotFoundException("album", "id", albumId));
    }

    @Override
    public Album updateAlbum(int albumId, Album album) {
        Album existingAlbum = albumRepository.findById(albumId).orElseThrow(() -> new ResourceNotFoundException("album", "id", albumId));

        if (existingAlbum != null) {
            existingAlbum.setAlbumName(album.getAlbumName());
            existingAlbum.setArtist(album.getArtist());
        }
        if (album.getMediaList() != null) {
            existingAlbum.setMediaList(album.getMediaList());
        }

        albumRepository.save(existingAlbum);
        logger.log(Level.WARN, "Updated album with id: " + albumId);
        return existingAlbum;
    }

    @Override
    public void deleteAlbum(int albumId) {
        Album album = albumRepository.findById(albumId).orElseThrow(() -> new ResourceNotFoundException("album", "id", albumId));

        for (Media media : album.getMediaList()) {
            media.getAlbums().remove(album);
            mediaRepository.save(media);
        }

        albumRepository.delete(album);
        logger.log(Level.WARN, "Deleted album with id: " + albumId);
    }

    public void setMediaRepository(MediaRepository mediaRepository) {
        this.mediaRepository = mediaRepository;
    }

    public void setAlbumRepository(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    public void setArtistRepository(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }
}
