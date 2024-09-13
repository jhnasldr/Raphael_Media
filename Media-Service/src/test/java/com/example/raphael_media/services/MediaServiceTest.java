package com.example.raphael_media.services;

import com.example.raphael_media.DTOs.MediaDTO;
import com.example.raphael_media.entities.*;
import com.example.raphael_media.exceptions.ResourceNotFoundException;
import com.example.raphael_media.repositores.MediaRepository;

import com.example.raphael_media.repositores.MusicRepository;
import com.example.raphael_media.repositores.PodcastRepository;
import com.example.raphael_media.repositores.VideoRepository;
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
    MusicRepository mockedMusicRepository;
    PodcastRepository mockedPodcastRepository;
    VideoRepository mockedVideoRepository;
    List<Media> mockedMediaList;

    private List<MediaDTO> mockedMediaDTOList;
    // private MediaRepository mockMediaRepository = mock(MediaRepository.class);
    private Music musicMedia;
    private Podcast podcastMedia;
    private Video videoMedia;
    private List<Genre> genreList = new ArrayList<>();
    private List<Artist> artistList = new ArrayList<>();
    private List<Album> albumsList = new ArrayList<>();

    private ResourceNotFoundException exception;
    private String expectation;


    @BeforeEach
    void setUp() {
        mockMedia = mock(Media.class);
        mockedMediaRepository = mock(MediaRepository.class);
        mockedMusicRepository = mock(MusicRepository.class);
        mockedPodcastRepository = mock(PodcastRepository.class);
        mockedVideoRepository = mock(VideoRepository.class);
        mediaService = new MediaService();
        mediaService.mediaRepository = mockedMediaRepository;
        mediaService.musicRepository = mockedMusicRepository;
        mediaService.podcastRepository = mockedPodcastRepository;
        mediaService.videoRepository = mockedVideoRepository;

        mockedMediaList = Arrays.asList(new Music("Music", "songTitle", LocalDate.now()),
                new Video("Video", "videoTitle", LocalDate.now())
        );

        mockedMediaDTOList = Arrays.asList(new MediaDTO(1, "songTitle", "Music"),
                new MediaDTO(2, "videoTitle", "Video")
        );

        musicMedia = new Music("titelMusic", "www.url.com", LocalDate.now());
        podcastMedia = new Podcast("titelPodcast", "www.url.com", LocalDate.now());
        videoMedia = new Video("titelVideo", "www.url.com", LocalDate.now());


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
        assertEquals("music", actualMediaDTOs.get(0).getMediaType());
    }

    @Test
    void getAllMediaDTO_shouldReturnListWithSizeTwo() {
        when(mockedMediaRepository.findAll()).thenReturn(mockedMediaList);
        List<MediaDTO> actualMediaDTOs = mediaService.getAllMediaDTO();
        assertEquals(2, actualMediaDTOs.size());
    }


    @Test
    void addNewMusic_WhenUsed_ShouldVerifyMethodSaveFromMediaRepository() {
        mediaService.addNewMusic(musicMedia);
        verify(mockedMusicRepository).save(musicMedia);
    }
    @Test
    void addNewPodcast_WhenUsed_ShouldVerifyMethodSaveFromMediaRepository() {
        mediaService.addNewPodcast(podcastMedia);
        verify(mockedPodcastRepository).save(podcastMedia);
    }
    @Test
    void addNewVideo_WhenUsed_ShouldVerifyMethodSaveFromMediaRepository() {
        mediaService.addNewVideo(videoMedia);
        verify(mockedVideoRepository).save(videoMedia);
    }

    @Test
    void updateMusic_ShouldReturnTrueWhenSavingUser() {
        //given
        musicMedia.setGenres(genreList);
        musicMedia.setAlbums(albumsList);
        musicMedia.setArtists(artistList);
        Music existningMusic;

        when(mockedMusicRepository.findById(1)).thenReturn(Optional.ofNullable(musicMedia));
        //when
        existningMusic = mediaService.updateMusic(1, musicMedia);
        //then
        verify(mockedMusicRepository).save(existningMusic);
    }

    @Test
    void updatePodcast_ShouldReturnTrueWhenSavingUser() {
        //given
        podcastMedia.setGenres(genreList);
        podcastMedia.setAlbums(albumsList);
        podcastMedia.setArtists(artistList);
        Podcast existningPodcast;

        when(mockedPodcastRepository.findById(1)).thenReturn(Optional.ofNullable(podcastMedia));
        //when
        existningPodcast = mediaService.updatePodcast(1, podcastMedia);
        //then
        verify(mockedPodcastRepository).save(existningPodcast);
    }

    @Test
    void updateMedia_ShouldReturnTrueWhenSavingUser() {
        //given
        videoMedia.setGenres(genreList);
        videoMedia.setAlbums(albumsList);
        videoMedia.setArtists(artistList);
        Video existningVideo;

        when(mockedVideoRepository.findById(1)).thenReturn(Optional.ofNullable(videoMedia));
        //when
        existningVideo = mediaService.updateVideo(1, videoMedia);
        //then
        verify(mockedVideoRepository).save(existningVideo);
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
            mediaService.updateMusic(3, musicMedia);
        });
        //then
        assertEquals(expectation, exception.getMessage());
    }
    @Test
    void updatePodcast_ShouldThrowExeptionResourceNotFound() {
        //given
        podcastMedia.setGenres(genreList);
        podcastMedia.setAlbums(albumsList);
        podcastMedia.setArtists(artistList);
        expectation = "podcast with id '3' was not found";

        when(mockedPodcastRepository.findById(1)).thenReturn(Optional.ofNullable(podcastMedia));
        //when
        exception = assertThrows(ResourceNotFoundException.class, () -> {
            mediaService.updatePodcast(3, podcastMedia);
        });
        //then
        assertEquals(expectation, exception.getMessage());
    }
    @Test
    void updateVideo_ShouldThrowExeptionResourceNotFound() {
        //given
        videoMedia.setGenres(genreList);
        videoMedia.setAlbums(albumsList);
        videoMedia.setArtists(artistList);
        expectation = "video with id '3' was not found";

        when(mockedVideoRepository.findById(1)).thenReturn(Optional.ofNullable(videoMedia));
        //when
        exception = assertThrows(ResourceNotFoundException.class, () -> {
            mediaService.updateVideo(3, videoMedia);
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
    void getMediaById_ShouldReturnTrue() {
        int id = 1;

        when(mockedMediaRepository.findById(id)).thenReturn(Optional.ofNullable(musicMedia));

        mediaService.getMediaById(id);

        verify(mockedMediaRepository).findById(id);
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
               Arrays.asList(new Music("Music", "songTitle", LocalDate.now())
       ));

       Media expectedMedia = new Music("Music", "songTitle", LocalDate.now());

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
                Arrays.asList(new Video("Video", "videoTitle", LocalDate.now())
                ));

        Media expectedMedia = new Video("Video", "videoTitle", LocalDate.now());

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
                Arrays.asList(new Podcast("Podcast", "podcastTitle", LocalDate.now())
                ));

        Media expectedMedia = new Podcast("Podcast", "podcastTitle", LocalDate.now());

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