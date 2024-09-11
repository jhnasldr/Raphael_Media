package com.example.raphael_media.services;

import com.example.raphael_media.DTOs.MediaDTO;
import com.example.raphael_media.entities.*;
import com.example.raphael_media.exceptions.ResourceNotFoundException;
import com.example.raphael_media.repositores.MediaRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MediaServiceTest {

    Media mockMedia;
    MediaService mediaService;
    MediaRepository mockedMediaRepository;
    List<Media> mockedMediaList;

    private List<MediaDTO> mockedMediaDTOList;
    // private MediaRepository mockMediaRepository = mock(MediaRepository.class);
    private Music musicMedia;
    private List<Genre> genreList = new ArrayList<>();
    private List<Artist> artistList = new ArrayList<>();
    private List<Album> albumsList = new ArrayList<>();

    private ResourceNotFoundException exception;
    private String expectation;


    @BeforeEach
    void setUp() {
        mockMedia = mock(Media.class);
        mockedMediaRepository = mock(MediaRepository.class);
        mediaService = new MediaService();
        mediaService.mediaRepository = mockedMediaRepository;

        mockedMediaList = Arrays.asList(new Media("Music", "songTitle", "URLForSong"),
                new Media("Video", "videoTitle", "URLForVideo")
        );

        mockedMediaDTOList = Arrays.asList(new MediaDTO(1, "songTitle", "Music"),
                new MediaDTO(2, "videoTitle", "Video")
        );

        musicMedia = new Music("titelMusic", "www.url.com", LocalDate.now());
        musicMedia = new Music("titelMusic", "www.url.com", LocalDate.now());


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
    void getAllMediaDTO_shouldReturnMediaTypeMusic() {
        when(mockedMediaRepository.findAll()).thenReturn(mockedMediaList);
        List<MediaDTO> actualMediaDTOs = mediaService.getAllMediaDTO();
        assertEquals(mockedMediaDTOList.get(0).getMediaType(), actualMediaDTOs.get(0).getMediaType());
        assertEquals("Music", actualMediaDTOs.get(0).getMediaType());
    }

    @Test
    void getAllMediaDTO_shouldReturnListWithSizeTwo() {
        when(mockedMediaRepository.findAll()).thenReturn(mockedMediaList);
        List<MediaDTO> actualMediaDTOs = mediaService.getAllMediaDTO();
        assertEquals(2, actualMediaDTOs.size());
    }


    @Test
    void addNewMedia_WhenUsed_ShouldVerifyMethodSaveFromMediaRepository() {
        mediaService.addNewMedia(mockMedia);
        verify(mockedMediaRepository).save(mockMedia);
    }

    @Test
    void updateMedia_ShouldReturnTrueWhenSavingUser() {
        //given
        musicMedia.setGenres(genreList);
        musicMedia.setAlbums(albumsList);
        musicMedia.setArtists(artistList);
        Media existningMedia;

        when(mockedMediaRepository.findById(1)).thenReturn(Optional.ofNullable(musicMedia));
        //when
        existningMedia = mediaService.updateMedia(1, musicMedia);
        //then
        verify(mockedMediaRepository).save(existningMedia);
    }

    @Test
    void updateMedia_ShouldThrowExeptionResourceNotFound() {
        //given
        musicMedia.setGenres(genreList);
        musicMedia.setAlbums(albumsList);
        musicMedia.setArtists(artistList);
        expectation = "music with id '3' was not found";

        when(mockedMediaRepository.findById(1)).thenReturn(Optional.ofNullable(musicMedia));
        //when
        exception = assertThrows(ResourceNotFoundException.class, () -> {
            mediaService.updateMedia(3, musicMedia);
        });
        //then
        assertEquals(expectation, exception.getMessage());
    }

    @Test
    void deleteMediaShouldRemoveMediaWhenIdExists() {
        int mediaId = 1;
        when(mockedMediaRepository.existsById(mediaId)).thenReturn(true);

        mediaService.deleteMediaById(mediaId);

        verify(mockedMediaRepository).deleteById(mediaId);
    }

    @Test
    void deleteMediaShouldThrowNotFoundExceptionWhenIdDoesNotExist() {
        int mediaId = 1;
        when(mockedMediaRepository.existsById(mediaId)).thenReturn(false);

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            mediaService.deleteMediaById(mediaId);
        });

        assertEquals("Media with id '1' was not found", exception.getMessage());
    }

    @Test
    void getMediaByTypeWhenMediaDoesNotExistShouldThrowResourceNotFoundException() {
        String mediaType = "NoMediaTypeFound";

        when(mockedMediaRepository.findByMediaType(mediaType)).thenReturn(Collections.emptyList());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            mediaService.getMediaByType(mediaType);
        });

        assertEquals("Media with type 'NoMediaTypeFound' was not found", exception.getMessage());
        verify(mockedMediaRepository, times(1)).findByMediaType(mediaType);
    }

    @Test
    void getMediaByTypeWhenMediaExistsShouldReturnMusic() {
       String mediaType = "Music";

       when(mockedMediaRepository.findByMediaType(mediaType)).thenReturn(
               Arrays.asList(new Media("Music", "songTitle", "URLForSong")
       ));

       Media expectedMedia = new Media("Music", "songTitle", "URLForSong");

       List<Media> result = mediaService.getMediaByType(mediaType);

       assertNotNull(result);
       assertEquals(1, result.size());
       Media actualMedia = result.get(0);
       assertEquals(expectedMedia.getTitle(), actualMedia.getTitle());
       assertEquals(expectedMedia.getURL(), actualMedia.getURL());
       assertEquals(expectedMedia.getMediaType(), actualMedia.getMediaType());
       verify(mockedMediaRepository, times(1)).findByMediaType(mediaType);
    }

    @Test
    void getMediaByTypeWhenMediaExistsShouldReturnVideo() {
        String mediaType = "Video";

        when(mockedMediaRepository.findByMediaType(mediaType)).thenReturn(
                Arrays.asList(new Media("Video", "videoTitle", "URLForVideo")
                ));

        Media expectedMedia = new Media("Video", "videoTitle", "URLForVideo");

        List<Media> result = mediaService.getMediaByType(mediaType);

        assertNotNull(result);
        assertEquals(1, result.size());
        Media actualMedia = result.get(0);
        assertEquals(expectedMedia.getTitle(), actualMedia.getTitle());
        assertEquals(expectedMedia.getURL(), actualMedia.getURL());
        assertEquals(expectedMedia.getMediaType(), actualMedia.getMediaType());
        verify(mockedMediaRepository, times(1)).findByMediaType(mediaType);
    }

    @Test
    void getMediaByTypeWhenMediaExistsShouldReturnPodcast() {
        String mediaType = "Podcast";

        when(mockedMediaRepository.findByMediaType(mediaType)).thenReturn(
                Arrays.asList(new Media("Podcast", "podcastTitle", "URLForPodcast")
                ));

        Media expectedMedia = new Media("Podcast", "podcastTitle", "URLForPodcast");

        List<Media> result = mediaService.getMediaByType(mediaType);

        assertNotNull(result);
        assertEquals(1, result.size());
        Media actualMedia = result.get(0);
        assertEquals(expectedMedia.getTitle(), actualMedia.getTitle());
        assertEquals(expectedMedia.getURL(), actualMedia.getURL());
        assertEquals(expectedMedia.getMediaType(), actualMedia.getMediaType());
        verify(mockedMediaRepository, times(1)).findByMediaType(mediaType);
    }
}