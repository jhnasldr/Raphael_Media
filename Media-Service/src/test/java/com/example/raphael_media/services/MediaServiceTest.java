package com.example.raphael_media.services;

import com.example.raphael_media.entities.Media;
import com.example.raphael_media.repositores.MediaRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class MediaServiceTest {

    Media mockMedia;
    MediaService mediaService;
    MediaRepository mockedMediaRepository;
    List<Media> mockedMediaList;


    @BeforeEach
    void setUp() {
        mockMedia = mock(Media.class);
        mockedMediaRepository = mock(MediaRepository.class);
        mediaService = new MediaService();
        mediaService.mediaRepository = mockedMediaRepository;

        mockedMediaList = Arrays.asList(new Media("Music", "songTitle", "URLForSong"),
                new Media("Vide", "videTitle", "URLForVideo")
        );
    }

    @Test
    void getAllMedia_WhenUsed_ShouldVerifyMethodFindAllFromMediaRepository() {
        mediaService.getAllMedia();
        verify(mockedMediaRepository).findAll();
    }

    @Test
    void getAllMedia_ShouldReturnListOfSizeTwo() {
        when(mockedMediaRepository.findAll()).thenReturn(mockedMediaList);
        assertThat(mediaService.getAllMedia()).hasSize(2);
    }


    @Test
    void addNewMedia_WhenUsed_ShouldVerifyMethodSaveFromMediaRepository() {
        mediaService.addNewMedia(mockMedia);
        verify(mockedMediaRepository).save(mockMedia);
    }
}