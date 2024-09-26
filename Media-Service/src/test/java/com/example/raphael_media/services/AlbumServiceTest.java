package com.example.raphael_media.services;

import com.example.raphael_media.entities.Album;
import com.example.raphael_media.entities.Artist;
import com.example.raphael_media.entities.Media;
import com.example.raphael_media.entities.Music;
import com.example.raphael_media.exceptions.ResourceNotFoundException;
import com.example.raphael_media.repositores.AlbumRepository;
import com.example.raphael_media.repositores.ArtistRepository;
import com.example.raphael_media.repositores.MediaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AlbumServiceTest {

    private AlbumRepository mockedAlbumRepository = mock(AlbumRepository.class);
    private MediaRepository mockedMediaRepository = mock(MediaRepository.class);
    private ArtistRepository mockedArtistRepository = mock(ArtistRepository.class);
    private AlbumService albumService = new AlbumService();
    private Album newAlbum1 = new Album();
    private Album newAlbum2 = new Album();
    private List<Media> mediaList = new ArrayList<>();
    private List<Album> albumList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        albumService.setAlbumRepository(mockedAlbumRepository);
        albumService.setMediaRepository(mockedMediaRepository);
        albumService.setArtistRepository(mockedArtistRepository);

        Artist artist = new Artist();
        artist.setArtistId(1);
        artist.setArtistName("Artist Name");

        newAlbum1 = new Album();
        newAlbum1.setAlbumName("New album 1");
        newAlbum1.setArtist(artist);
        newAlbum1.setMediaList(mediaList);

        newAlbum2 = new Album();
        newAlbum2.setAlbumName("New album 2");
        newAlbum2.setArtist(artist);
        newAlbum2.setMediaList(new ArrayList<>());


        albumList.add(newAlbum1);
        albumList.add(newAlbum2);
    }
    @Test
    void testAddAlbum_Success() {
        when(mockedArtistRepository.existsById(any(Integer.class))).thenReturn(true);
        when(mockedAlbumRepository.save(any(Album.class))).thenReturn(newAlbum1);

        Album result = albumService.addAlbum(newAlbum1);

        assertNotNull(result);
        assertEquals("New album 1", result.getAlbumName());
        verify(mockedAlbumRepository).save(newAlbum1);
    }

    @Test
    void testAddAlbum_ArtistNotFound() {
        when(mockedArtistRepository.existsById(any(Integer.class))).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> {
            albumService.addAlbum(newAlbum1);
        });
    }

    @Test
    void testFetchAllAlbums() {
        when(mockedAlbumRepository.findAll()).thenReturn(albumList);

        List<Album> result = albumService.getAllAlbums();

        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    void testFetchAlbum_Success() {
        when(mockedAlbumRepository.findById(any(Integer.class))).thenReturn(Optional.of(newAlbum1));

        Album result = albumService.getAlbum(1);

        assertNotNull(result);
        assertEquals("New album 1", result.getAlbumName());
    }

    @Test
    void testFetchAlbum_NotFound(){
        when(mockedAlbumRepository.findById(any(Integer.class))).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            albumService.getAlbum(1);
        });
    }


    @Test
    void testUpdateAlbum_Success() {
        when(mockedAlbumRepository.findById(any(Integer.class))).thenReturn(Optional.of(newAlbum1));
        when(mockedAlbumRepository.save(any(Album.class))).thenReturn(newAlbum1);

        newAlbum1.setAlbumName("Updated album name");
        Album result = albumService.updateAlbum(1, newAlbum1);

        assertNotNull(result);
        assertEquals("Updated album name", result.getAlbumName());
    }

    @Test
    void testUpdateAlbum_NotFound() {
        when(mockedAlbumRepository.findById(any(Integer.class))).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            albumService.updateAlbum(1, newAlbum1);
        });
    }

    @Test
    void testDeleteAlbum_Success() {
        Media media1 = new Music();
        Media media2 = new Music();
        media1.setAlbums(new ArrayList<>(Arrays.asList(newAlbum1)));
        media2.setAlbums(new ArrayList<>(Arrays.asList(newAlbum1)));

        newAlbum1.setMediaList(Arrays.asList(media1, media2));

        when(mockedAlbumRepository.findById(anyInt())).thenReturn(Optional.of(newAlbum1));
        when(mockedMediaRepository.save(any(Media.class))).thenAnswer(invocation -> invocation.getArgument(0));
        doNothing().when(mockedAlbumRepository).delete(any(Album.class));

        albumService.deleteAlbum(1);

        verify(mockedAlbumRepository).delete(newAlbum1);

        assertTrue(media1.getAlbums().isEmpty());
        assertTrue(media2.getAlbums().isEmpty());

        verify(mockedMediaRepository).save(media1);
        verify(mockedMediaRepository).save(media2);
    }

    @Test
    void testDeleteAlbum_NotFound() {
        when(mockedAlbumRepository.findById(any(Integer.class))).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            albumService.deleteAlbum(1);
        });
    }
}