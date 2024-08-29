package com.example.raphael_media.entities;

import java.time.LocalDateTime;
import java.util.List;

public interface Media {

    String title();
    String URL();
    LocalDateTime releaseDate();
    List<Artist> listOfArtists();
    List<Album> listOfAlbums();
    List<Genre> listOfGenres();




}
