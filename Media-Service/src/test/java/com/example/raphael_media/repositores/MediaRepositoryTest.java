package com.example.raphael_media.repositores;

import com.example.raphael_media.entities.Media;
import com.example.raphael_media.entities.Music;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
class MediaRepositoryTest {

    @Autowired
    MediaRepository mediaRepository;
    Music testMusic;

    @BeforeEach
    void setUp() {
        testMusic = new Music();
        testMusic.setTitle("Test Title");
        testMusic.setMediaType("Music");
        testMusic.setURL("http://example.com/test");
        mediaRepository.save(testMusic);
    }

    @Test
    void testFindByMediaType_ReturnsCorrectMedia() {
        List<Media> foundMedia = mediaRepository.findByMediaType(testMusic.getMediaType());

        assertThat(foundMedia).isNotEmpty();
        assertThat(foundMedia.get(0).getTitle()).isEqualTo("Test Title");
    }

    @Test
    void testFindMediaByType_WhenMediaNotFound_ShouldReturnEmptyList() {
        String mediaType = "NonExistentType";

        List<Media> foundMedia = mediaRepository.findByMediaType(mediaType);

        assertThat(foundMedia).isEmpty();
    }
}