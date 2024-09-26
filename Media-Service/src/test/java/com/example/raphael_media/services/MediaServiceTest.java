package com.example.raphael_media.services;

import com.example.raphael_media.DTOs.MediaDTO;
import com.example.raphael_media.entities.*;
import com.example.raphael_media.exceptions.ResourceNotFoundException;
import com.example.raphael_media.repositores.*;

import org.apache.log4j.Level;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    GenreRepository mockedGenreRepository;
    ArtistRepository mockedArtistRepository;
    AlbumRepository mockedAlbumRepository;
    List<Media> mockedMediaList;
    private List<MediaDTO> mockedMediaDTOList;
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
        mockedGenreRepository = mock(GenreRepository.class);
        mockedAlbumRepository = mock(AlbumRepository.class);
        mockedArtistRepository = mock(ArtistRepository.class);
        mediaService = new MediaService();
        mediaService.setMediaRepository(mockedMediaRepository);
        mediaService.setMusicRepository(mockedMusicRepository);
        mediaService.setPodcastRepository(mockedPodcastRepository);
        mediaService.setVideoRepository(mockedVideoRepository);
        mediaService.setAlbumRepository(mockedAlbumRepository);
        mediaService.setArtistRepository(mockedArtistRepository);
        mediaService.setGenreRepository(mockedGenreRepository);

        mockedMediaList = Arrays.asList(new Music("Music", "songTitle", LocalDate.now()),
                new Video("Video", "videoTitle", LocalDate.now())
        );

        mockedMediaDTOList = new ArrayList<>(Arrays.asList(new MediaDTO(1, "songTitle", "Music"),
                new MediaDTO(2, "videoTitle", "Video")
        ));

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
        assertEquals("Music", actualMediaDTOs.get(0).getMediaType());
    }

    @Test
    void getAllMediaDTO_shouldReturnListWithSizeTwo() {
        when(mockedMediaRepository.findAll()).thenReturn(mockedMediaList);
        List<MediaDTO> actualMediaDTOs = mediaService.getAllMediaDTO();
        assertEquals(2, actualMediaDTOs.size());
    }

    @Test
    void getListOfMediaDTOFromListOfIds_ShouldReturnMediaTypeVideo() {
        List<Integer> listOfId = Arrays.asList(1, 2);
        mockedMediaList.get(0).setId(1);
        mockedMediaList.get(1).setId(2);
        when(mockedMediaRepository.findAllById(listOfId)).thenReturn(mockedMediaList);
        List<MediaDTO> actualMediaDTOs = mediaService.getListOfMediaDTOFromListOfIds(listOfId);

        assertEquals("Video", actualMediaDTOs.get(1).getMediaType());
    }

    @Test
    void getListOfMediaDTOFromListOfIds_ShouldReturnListOfSizeTwo() {
        mockedMediaList.get(0).setId(1);
        mockedMediaList.get(1).setId(2);

        List<Integer> listOfId = List.of(1, 2);
        when(mockedMediaRepository.findAllById(listOfId)).thenReturn(mockedMediaList);
        List<MediaDTO> actualMediaDTOs = mediaService.getListOfMediaDTOFromListOfIds(listOfId);
        assertEquals(2, actualMediaDTOs.size());
    }

    @Test
    void addNewMedia_ShouldVerifyThatMusicWasSaved() {
        mediaService.addNewMedia(musicMedia);
        verify(mockedMusicRepository).save(musicMedia);
    }

    @Test
    void addNewMedia_ShouldVerifyThatVideoWasSaved() {
        mediaService.addNewMedia(podcastMedia);
        verify(mockedPodcastRepository).save(podcastMedia);
    }

    @Test
    void addNewMedia_ShouldVerifyThatPodcastWasSaved() {
        mediaService.addNewMedia(videoMedia);
        verify(mockedVideoRepository).save(videoMedia);
    }

    @Test
    void addNewMedia_ShouldThrowExeptionResourceNotFoundWhenMediaNotFound() {
        Media media = new Music();
        media.setMediaType("Sko");
        expectation = "Unknown media type: Sko";

        IllegalArgumentException exceptiontwo = assertThrows(IllegalArgumentException.class, () -> {
            mediaService.addNewMedia(media);
        });

        assertEquals(expectation, exceptiontwo.getMessage());
    }

    @Test
    void updateMedia_ShouldThrowExeptionResourceNotFoundWhenMediaNotFound() {
        Media media = new Music();
        media.setMediaType("Sko");
        expectation = "Unknown media type: Sko";

        IllegalArgumentException exceptiontwo = assertThrows(IllegalArgumentException.class, () -> {
            mediaService.updateMedia(1, media);
        });

        assertEquals(expectation, exceptiontwo.getMessage());
    }

    @Test
    void updateMedia_ShouldReturnTrueWhenUpdateAndSavedPodcast() {
        podcastMedia.setGenres(genreList);
        podcastMedia.setAlbums(albumsList);
        podcastMedia.setArtists(artistList);
        Podcast existningPodcast;

        when(mockedPodcastRepository.findById(1)).thenReturn(Optional.ofNullable(podcastMedia));

        existningPodcast = (Podcast) mediaService.updateMedia(1, podcastMedia);

        verify(mockedPodcastRepository).save(existningPodcast);
    }

    @Test
    void updateMedia_ShouldReturnTrueWhenUpdateAndSavedMusic() {
        musicMedia.setGenres(genreList);
        musicMedia.setAlbums(albumsList);
        musicMedia.setArtists(artistList);
        Music existningMusic;

        when(mockedMusicRepository.findById(1)).thenReturn(Optional.ofNullable(musicMedia));

        existningMusic = (Music) mediaService.updateMedia(1, musicMedia);

        verify(mockedMusicRepository).save(existningMusic);
    }

    @Test
    void updateMedia_ShouldReturnTrueWhenUpdateAndSavedVideo() {
        videoMedia.setGenres(genreList);
        videoMedia.setAlbums(albumsList);
        videoMedia.setArtists(artistList);
        Video existningVideo;

        when(mockedVideoRepository.findById(1)).thenReturn(Optional.ofNullable(videoMedia));

        existningVideo = (Video) mediaService.updateMedia(1, videoMedia);

        verify(mockedVideoRepository).save(existningVideo);
    }

    @Test
    void updateMedia_ShouldThrowExeptionResourceNotFoundWhenMusicNotFound() {
        musicMedia.setGenres(genreList);
        musicMedia.setAlbums(albumsList);
        musicMedia.setArtists(artistList);
        expectation = "Music with id '3' was not found";

        when(mockedMediaRepository.findById(1)).thenReturn(Optional.ofNullable(musicMedia));

        exception = assertThrows(ResourceNotFoundException.class, () -> {
            mediaService.updateMedia(3, musicMedia);
        });

        assertEquals(expectation, exception.getMessage());
    }

    @Test
    void updateMedia_ShouldThrowExeptionResourceNotFoundWhenPodcastNotFound() {
        podcastMedia.setGenres(genreList);
        podcastMedia.setAlbums(albumsList);
        podcastMedia.setArtists(artistList);
        expectation = "Podcast with id '3' was not found";

        when(mockedPodcastRepository.findById(1)).thenReturn(Optional.ofNullable(podcastMedia));

        exception = assertThrows(ResourceNotFoundException.class, () -> {
            mediaService.updateMedia(3, podcastMedia);
        });

        assertEquals(expectation, exception.getMessage());
    }

    @Test
    void updateMedia_ShouldThrowExeptionResourceNotFoundWhenVideoNotFound() {
        videoMedia.setGenres(genreList);
        videoMedia.setAlbums(albumsList);
        videoMedia.setArtists(artistList);
        expectation = "Video with id '3' was not found";

        when(mockedVideoRepository.findById(1)).thenReturn(Optional.ofNullable(videoMedia));

        exception = assertThrows(ResourceNotFoundException.class, () -> {
            mediaService.updateMedia(3, videoMedia);
        });

        assertEquals(expectation, exception.getMessage());
    }


    @Test
    void deleteMedia_ShouldRemoveMediaWhenIdExists() {
        Media media = new Music();
        List<Media> mediaList = new ArrayList<>();
        mediaList.add(media);

        Genre genre = new Genre();
        List<Genre> genreList1 = new ArrayList<>();
        genre.setMediaList(mediaList);

        Artist artist = new Artist();
        List<Artist> artistList1 = new ArrayList<>();
        artist.setMediaList(mediaList);

        Album album = new Album();
        List<Album> albumList1 = new ArrayList<>();
        album.setMediaList(mediaList);

        albumList1.add(album);
        artistList1.add(artist);
        genreList1.add(genre);

        media.setArtists(artistList1);
        media.setAlbums(albumList1);
        media.setGenres(genreList1);


        when(mockedMediaRepository.findById(1)).thenReturn(Optional.of(media));

        mediaService.deleteMediaById(1);

        verify(mockedMediaRepository).delete(media);
        verify(mockedGenreRepository).save(genre);
        verify(mockedArtistRepository).save(artist);
        verify(mockedAlbumRepository).save(album);

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