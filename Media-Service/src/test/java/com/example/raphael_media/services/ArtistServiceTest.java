package com.example.raphael_media.services;

import com.example.raphael_media.entities.Album;
import com.example.raphael_media.entities.Artist;
import com.example.raphael_media.entities.Media;
import com.example.raphael_media.repositores.ArtistRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ArtistServiceTest {

    ArtistService artistService;
    ArtistRepository mockedArtistRepository;

    @BeforeEach
    void setUp() {
        mockedArtistRepository = mock(ArtistRepository.class);
        artistService = new ArtistService();
        artistService.artistRepository = mockedArtistRepository;
    }

    @Test
    void testGetAllAlbumsByArtistIdWhenArtistExists() {
        List<Album> albums = createListOfAlbums();

        Artist artist = new Artist();
        artist.setArtistId(1);
        artist.setAlbums(albums);

        when(mockedArtistRepository.findById(1)).thenReturn(Optional.of(artist));

        List<Album> result = artistService.getAllAlbumsByArtistId(1);

        assertEquals(albums.size(), result.size());
        assertEquals(albums, result);
    }
    @Test
    void testGetAllAlbumsByArtistIdWhenArtistDoesNotExist() {
        when(mockedArtistRepository.findById(1)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            artistService.getAllAlbumsByArtistId(1);
        });

        assertEquals("Artist with id 1 not found", exception.getMessage());
    }

    //Hjälpmetod för att skapa en lista med albums
    private List<Album> createListOfAlbums() {
        Album album1 = new Album();
        album1.setAlbumId(1);
        album1.setAlbumName("Album 1");

        Album album2 = new Album();
        album2.setAlbumId(2);
        album2.setAlbumName("Album 2");

        return new ArrayList<>(List.of(album1, album2));
    }

    @Test
    void getMediaByArtistReturnsMediaListCorrectlyWhenArtistExists() {
        int artistId = 1;
        List<Media> mediaList = new ArrayList<>();
        mediaList.add(new Media());
        mediaList.add(new Media());

        Artist artist = new Artist();
        artist.setMediaList(mediaList);

        when(mockedArtistRepository.findById(artistId)).thenReturn(Optional.of(artist));

        List<Media> result = artistService.getAllMediaByArtistId(artistId);

        assertEquals(mediaList.size(), result.size());
        assertEquals(mediaList, result);
    }

    @Test
    void getAllMediaByArtistThrowsExceptionWhenArtistNotFound() {
        int artistId = 1;
        when(mockedArtistRepository.findById(artistId)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            artistService.getAllMediaByArtistId(artistId);
        });

        assertEquals("Artist with id " + artistId + " not found", exception.getMessage());
    }
}