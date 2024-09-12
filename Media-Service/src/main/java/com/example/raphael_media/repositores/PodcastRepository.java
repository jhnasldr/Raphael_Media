package com.example.raphael_media.repositores;

import com.example.raphael_media.entities.Media;
import com.example.raphael_media.entities.Podcast;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PodcastRepository extends JpaRepository<Podcast, Integer> {

}
