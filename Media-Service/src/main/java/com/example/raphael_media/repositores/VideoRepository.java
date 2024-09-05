package com.example.raphael_media.repositores;

import com.example.raphael_media.entities.Music;
import com.example.raphael_media.entities.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
//
@Repository
public interface VideoRepository extends JpaRepository<Video,Integer> {
}
