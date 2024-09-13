package com.example.raphael_media.repositores;

import com.example.raphael_media.entities.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Integer> {
}
