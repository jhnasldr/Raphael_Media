package com.example.raphael_media.services;


import com.example.raphael_media.entities.*;
import com.example.raphael_media.exceptions.ResourceNotFoundException;
import com.example.raphael_media.repositores.GenreRepository;
import com.example.raphael_media.repositores.MediaRepository;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class GenreService implements GenreServiceInterface{
    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private MediaRepository mediaRepository;

    Logger logger = Logger.getLogger(ArtistService.class);

    @Override
    public Genre addGenre(Genre genre) {
        genreRepository.save(genre);
        logger.log(Level.WARN, "New genre with id: " + genre.getGenreId() + " created");
        return genre;
    }

    @Override
    public List<Genre> fetchAllGenre() {
        return genreRepository.findAll();
    }

    @Override
    public Genre fetchGenre(int genreId) {
        return genreRepository.findById(genreId).orElseThrow(()->new ResourceNotFoundException("genre", "id", genreId));
    }

    @Override
    public Genre updateGenre(int genreId, Genre genre) {
        Genre existingGenre = genreRepository.findById(genreId).orElseThrow(()->new ResourceNotFoundException("genre", "id", genreId));
        if(genre.getGenre() != null){
            existingGenre.setGenre(genre.getGenre());
        }
        if(genre.getMediaList() != null){
            existingGenre.setMediaList(genre.getMediaList());
        }

        genreRepository.save(existingGenre);
        logger.log(Level.WARN, "Genre with id: " + genreId + " updated");
        return existingGenre;
    }

    @Override
    public void deleteGenre(int genreId) {
        Genre genre = genreRepository.findById(genreId).orElseThrow(()->new ResourceNotFoundException("genre", "id", genreId));

        for (Media media : genre.getMediaList()) {
            media.getGenres().remove(genre);
            mediaRepository.save(media);
        }

        logger.log(Level.WARN, "Genre with id: " + genreId + " updated");
        genreRepository.delete(genre);
    }

    public void setGenreRepository(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public void setMediaRepository(MediaRepository mediaRepository) {
        this.mediaRepository = mediaRepository;
    }
}
