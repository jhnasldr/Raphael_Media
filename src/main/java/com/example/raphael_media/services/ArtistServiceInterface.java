package com.example.raphael_media.services;

import com.example.raphael_media.entities.Album;

import java.util.List;

public interface ArtistServiceInterface {
    List<Album> getAllAlbumsByArtistId(int artistId);
}
