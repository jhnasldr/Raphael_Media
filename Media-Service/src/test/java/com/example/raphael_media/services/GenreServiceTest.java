package com.example.raphael_media.services;

import com.example.raphael_media.entities.*;
import com.example.raphael_media.exceptions.ResourceNotFoundException;
import com.example.raphael_media.repositores.GenreRepository;
import com.example.raphael_media.repositores.MediaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GenreServiceTest {
    private GenreRepository mockGenreRepository = mock(GenreRepository.class);
    private MediaRepository mockMediaRepository = mock(MediaRepository.class);
    private GenreService genreService = new GenreService();
    private Genre genreRock = new Genre("rock");
    private Genre genrePop = new Genre("pop");
    private List<Media> mediaList = new ArrayList<>();
    private List<Genre> genreList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        genreService.setGenreRepository(mockGenreRepository);
        genreService.setMediaRepository(mockMediaRepository);
        mediaList.add(new Music());
        mediaList.add(new Music());
        mediaList.add(new Music());
    }

    @Test
    void addGenre_ShouldReturnTrueWhenAddGenre() {
        when(mockGenreRepository.save(genreRock)).thenReturn(genreRock);

        Genre reusltuGenre = genreService.addGenre(genreRock);

        verify(mockGenreRepository).save(genreRock);
        assertTrue(reusltuGenre == genreRock);
    }

    @Test
    void fetchAllGenre_ShouldReturnAListOfGenre() {
        when(mockGenreRepository.findAll()).thenReturn(genreList);

        genreService.getAllGenres();

        verify(mockGenreRepository).findAll();
    }

    @Test
    void fetchGenre_ShouldReturnTureWhenFindByIdOne() {
        when(mockGenreRepository.findById(1)).thenReturn(Optional.ofNullable(genreRock));

        Genre resultGenre = genreService.getGenre(1);

        assertTrue(genreRock == resultGenre);
        verify(mockGenreRepository).findById(1);
    }

    @Test
    void fetchGenre_ShouldThrowExceptionWhenGenreNotFind() {
        when(mockGenreRepository.findById(1)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            genreService.getGenre(2);
        });

        assertEquals("genre with id '2' was not found", exception.getMessage());
    }

    @Test
    void updateGenre_ShouldReturnTrueSaveWhenUpdateIsDone() {
        genreRock.setGenreId(1);

        Media media1 = new Music();
        media1.setGenres(new ArrayList<>(Arrays.asList(genreRock)));

        genreRock.setMediaList(Arrays.asList(media1));

        when(mockGenreRepository.findById(1)).thenReturn(Optional.ofNullable(genreRock));

        genreService.updateGenre(1, genreRock);

        verify(mockGenreRepository).save(genreRock);
    }

    @Test
    void updateGenre_ShouldThrowExceptionWhenGenreNotFind() {
        when(mockGenreRepository.findById(1)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            genreService.updateGenre(2, genrePop);
        });

        assertEquals("genre with id '2' was not found", exception.getMessage());
    }

    @Test
    void deleteGenre_ShouldRemoveGenreFromMediaAndDeleteGenre() {
        Genre genre = new Genre();
        genre.setGenreId(1);

        Media media1 = new Music();
        media1.setGenres(new ArrayList<>(Arrays.asList(genre)));
        Media media2 = new Music();
        media2.setGenres(new ArrayList<>(Arrays.asList(genre)));

        genre.setMediaList(Arrays.asList(media1, media2));

        when(mockGenreRepository.findById(1)).thenReturn(Optional.of(genre));

        genreService.deleteGenre(1);

        assertFalse(media1.getGenres().contains(genre));
        assertFalse(media2.getGenres().contains(genre));

        verify(mockMediaRepository).save(media1);
        verify(mockMediaRepository).save(media2);
        verify(mockGenreRepository).delete(genre);
    }

    @Test
    void deleteGenre_ShouldThrowExceptionWhenGenreNotFind() {
        when(mockGenreRepository.findById(1)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            genreService.deleteGenre(2);
        });

        assertEquals("genre with id '2' was not found", exception.getMessage());
    }
}