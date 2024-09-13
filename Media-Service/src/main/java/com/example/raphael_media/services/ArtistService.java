package com.example.raphael_media.services;

import com.example.raphael_media.entities.Album;
import com.example.raphael_media.entities.Artist;
import com.example.raphael_media.entities.Media;
import com.example.raphael_media.repositores.ArtistRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistService implements ArtistServiceInterface {

    @Autowired
    public ArtistRepository artistRepository;

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

}
