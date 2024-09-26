package com.example.raphael_media.services;

import com.example.raphael_media.entities.*;
import com.example.raphael_media.exceptions.ResourceNotFoundException;
import com.example.raphael_media.repositores.ArtistRepository;
import com.example.raphael_media.repositores.MediaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ArtistServiceTest {
    ArtistService artistService;
    private MediaRepository mockMediaRepository = mock(MediaRepository.class);
    ArtistRepository mockedArtistRepository;
    private Artist artist = new Artist();
    private List<Album> albumList = new ArrayList<>();
    private List<Media> mediaList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        mockedArtistRepository = mock(ArtistRepository.class);
        artistService = new ArtistService();
        artistService.artistRepository = mockedArtistRepository;
        artistService.setMediaRepository(mockMediaRepository);

        artist.setArtistName("Tome");
        albumList.add(new Album());
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
        mediaList.add(new Music());
        mediaList.add(new Music());

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

    @Test
    void addArtist_ShouldReturnTrueWhenAddArtist() {
        when(mockedArtistRepository.save(artist)).thenReturn(artist);

        Artist reusltuArtist = artistService.addArtist(artist);

        verify(mockedArtistRepository).save(artist);
        assertTrue(reusltuArtist == artist);
    }

    @Test
    void fetchAllArtist_ShouldReturnAListOfArtist() {
        List<Artist> artistList = new ArrayList<>();
        artistList.add(artist);
        when(mockedArtistRepository.findAll()).thenReturn(artistList);

        artistService.getAllArtists();

        verify(mockedArtistRepository).findAll();
    }

    @Test
    void fetchArtist_ShouldReturnTureWhenFindByIdOne() {
        when(mockedArtistRepository.findById(1)).thenReturn(Optional.ofNullable(artist));

        Artist resultArtist = artistService.getArtist(1);

        assertTrue(artist == resultArtist);
        verify(mockedArtistRepository).findById(1);
    }

    @Test
    void fetchArtist_ShouldThrowExceptionWhenArtistNotFind() {
        when(mockedArtistRepository.findById(1)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            artistService.getArtist(2);
        });

        assertEquals("artist with id '2' was not found", exception.getMessage());
    }

    @Test
    void updateArtist_ShouldReturnTrueSaveWhenUpdateIsDone() {
        artist.setArtistId(1);

        Media media1 = new Music();
        media1.setArtists(new ArrayList<>(Arrays.asList(artist)));

        artist.setMediaList(Arrays.asList(media1));
        albumList.add(new Album());
        artist.setAlbums(albumList);

        when(mockedArtistRepository.findById(1)).thenReturn(Optional.ofNullable(artist));

        artistService.updateArtist(1, artist);

        verify(mockedArtistRepository).save(artist);
    }

    @Test
    void updateArtist_ShouldThrowExceptionWhenArtisteNotFind() {
        when(mockedArtistRepository.findById(1)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            artistService.updateArtist(2, artist);
        });

        assertEquals("artist with id '2' was not found", exception.getMessage());
    }

    @Test
    void deleteArtist_ShouldRemoveGenreFromMediaAndDeleteArtist() {
        Artist artist2 = new Artist();
        artist2.setArtistId(1);

        Media media1 = new Music();
        Media media2 = new Music();

        List<Artist> artistList = new ArrayList<>();
        artistList.add(artist2);
        artistList.add(artist);
        media1.setArtists(artistList);
        media2.setArtists(artistList);
        mediaList.add(media1);
        mediaList.add(media2);

        artist2.setMediaList(mediaList);

        when(mockedArtistRepository.findById(1)).thenReturn(Optional.of(artist2));

        artistService.deleteArtist(1);

        verify(mockMediaRepository).save(media1);
        verify(mockMediaRepository).save(media2);
        verify(mockedArtistRepository).delete(artist2);
    }

    @Test
    void deleteArtist_ShouldThrowExceptionWhenArtistNotFind() {
        when(mockedArtistRepository.findById(1)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            artistService.deleteArtist(2);
        });

        assertEquals("Artist with id '2' was not found", exception.getMessage());
    }
}