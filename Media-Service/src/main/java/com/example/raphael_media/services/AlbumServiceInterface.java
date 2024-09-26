package com.example.raphael_media.services;

import com.example.raphael_media.entities.Album;

import java.util.List;

public interface AlbumServiceInterface {

    Album addAlbum(Album album);

    List<Album> getAllAlbums();

    Album getAlbum(int albumId);

    Album updateAlbum(int albumId, Album album);

    void deleteAlbum(int albumId);
}
