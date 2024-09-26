package com.example.raphael_media.repositores;

import com.example.raphael_media.entities.Media;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MediaRepository extends JpaRepository<Media, Integer> {
    List<Media> findByMediaType(String mediaType); //Todo X
}
