package com.example.raphael_media.services;

import com.example.raphael_media.entities.Album;
import com.example.raphael_media.entities.Artist;
import com.example.raphael_media.entities.Genre;
import com.example.raphael_media.entities.Media;

import java.util.List;

public interface ArtistServiceInterface {
    List<Album> getAllAlbumsByArtistId(int artistId);
    List<Media> getAllMediaByArtistId(int artistId);

    Artist addArtist(Artist artist);

    List<Artist> fetchAllArtist();

    Artist fetchArtist(int artistId);

    Artist updateArtist(int artistId, Artist artist);

    void deleteArtist(int artistId);
}
