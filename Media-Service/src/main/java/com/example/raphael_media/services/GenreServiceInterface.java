package com.example.raphael_media.services;

import com.example.raphael_media.entities.Genre;

import java.util.List;

public interface GenreServiceInterface {

    Genre addGenre(Genre genre);

    List<Genre> getAllGenres();

    Genre getGenre(int genreId);

    Genre updateGenre(int genreId, Genre genre);

    void deleteGenre(int genreId);
}
