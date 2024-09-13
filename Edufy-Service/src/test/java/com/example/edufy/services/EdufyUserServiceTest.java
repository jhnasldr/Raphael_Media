package com.example.edufy.services;

import com.example.edufy.VO.Customer;
import com.example.edufy.VO.Genre;
import com.example.edufy.VO.Media;
import com.example.edufy.VO.MediaInteractions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EdufyUserServiceTest {
    // private EdufyUserService edufyUserService = new EdufyUserService();
    private EdufyUserService edufyUserService;
    // private RestTemplate mockRestTemplate = mock(RestTemplate.class);
    private RestTemplate mockRestTemplate;

    private Customer customerTest;
    private Media mediaTest;
    private MediaInteractions mediaInteractionsTest;
    private List<MediaInteractions> testList = new ArrayList<>();
    private Customer mockCustomer;
    private Media media1;
    private Media media2;
    private Media media3;
    private List<Media> mediaList;
    private MediaInteractions mediaInteractions1;
    private MediaInteractions mediaInteractions2;
    private MediaInteractions mediaInteractions3;
    private List<MediaInteractions> mediaInteractionsList;


    @BeforeEach
    void setUp() {
        edufyUserService = new EdufyUserService();
        mockRestTemplate = mock(RestTemplate.class);
        mediaInteractionsTest = new MediaInteractions(1, 1, null, 0);
        mediaTest = new Media(1, "music", "testmusic", "www.TestMusic.com", LocalDate.now());
        testList.add(mediaInteractionsTest);
        customerTest = new Customer(1, "huggoTest", "huggo@Test.com", testList);
        edufyUserService.setRestTemplate(mockRestTemplate);
        mockCustomer = mock(Customer.class);
//2, 1 3
        mediaInteractions1 = new MediaInteractions(1, 1, "liked", 3);
        mediaInteractions2 = new MediaInteractions(2, 2, "liked", 5);
        mediaInteractions3 = new MediaInteractions(3, 3, "disliked", 1);
        mediaInteractions1.setCustomer(mockCustomer);
        mediaInteractions2.setCustomer(mockCustomer);
        mediaInteractions3.setCustomer(mockCustomer);
        mediaInteractionsList = Arrays.asList(mediaInteractions1, mediaInteractions2, mediaInteractions3);
        // Mock the RestTemplate postForObject call
        media1 = new Media(1, "music1", "testmusic1", "www.TestMusic1.com", LocalDate.now());
        media2 = new Media(2, "music2", "testmusic2", "www.TestMusic2.com", LocalDate.now());
        media3 = new Media(3, "music3", "testmusic3", "www.TestMusic3.com", LocalDate.now());
//        mediaList = Arrays.asList(media1, media2, media3);
        mediaList = new ArrayList<>();
        mediaList.add(media1);
        mediaList.add(media2);
        mediaList.add(media3);
    }

    @Test
    void playMediaAndIncreaseTimesListenedTo_ShouldIncreasePlayCountWhenMediaInteractionsIsEqualMedia() {
        //given
        int idCustomer = 1;
        int idMedia = 1;

        String customerGetURL = "http://customer-service/api/customer/1";
        String mediaGetURL = "http://media-service/api/media/1";
        String customerPutURl = "http://customer-service/api/customer/updatecustomer/1";

        when(mockRestTemplate.getForObject(mediaGetURL, Media.class)).thenReturn(mediaTest);
        when(mockRestTemplate.getForObject(customerGetURL, Customer.class)).thenReturn(customerTest);

        //when
        edufyUserService.playAndUpdateListedToInCustomer(idCustomer, idMedia);

        //then
        verify(mockRestTemplate).put(customerPutURl, customerTest);
        assertEquals(1, mediaInteractionsTest.getTimesListenedTo());
    }

    @Test
    void playMediaAndIncreaseTimesListenedTo_ShouldAddAIndexInCustomerList() {
        //given
        int idCustomer = 1;
        int idMedia = 1;

        String customerGetURL = "http://customer-service/api/customer/1";
        String mediaGetURL = "http://media-service/api/media/1";
        String customerPutURl = "http://customer-service/api/customer/updatecustomer/1";

        mediaTest = new Media(2, "Podcast", "Sagon om", "www.pod.com", LocalDate.now());
        when(mockRestTemplate.getForObject(customerGetURL, Customer.class)).thenReturn(customerTest);
        when(mockRestTemplate.getForObject(mediaGetURL, Media.class)).thenReturn(mediaTest);

        //when
        edufyUserService.playAndUpdateListedToInCustomer(idCustomer, idMedia);

        //then
        verify(mockRestTemplate).put(customerPutURl, customerTest);
        assertEquals(2, customerTest.getMediaInteractions().size());
    }

    @Test
    void setRestTemplate() {
    }

    //malin
    // vill kolla att vi får fram listan i rätt ordning
    // kolla att när man anger listan i rätt ordning så skickar den faktisk rätt media
    @Test
    void getMostPlayedMediaForUserById_ShouldReturnListOf3() {
        when(mockRestTemplate.getForObject("http://customer-service/api/customer/" + 1, Customer.class))
                .thenReturn(mockCustomer);

        when(mockCustomer.getMediaInteractions()).thenReturn(mediaInteractionsList);

        Media[] mediaArraySortedByTimesListenedTo = {media2, media1, media3};

        when(mockRestTemplate.postForObject("http://Media-Service/api/media/getlistofmediadtofromlistofid", Arrays.asList(2, 1, 3), Media[].class))
                .thenReturn(mediaArraySortedByTimesListenedTo);

        List<Media> sortedListOfMedia = edufyUserService.getMostPlayedMediaForUserById(1);

        assertEquals(3, sortedListOfMedia.size());
    }

    @Test
    void getMostPlayedMediaForUserById_ShouldReturnMediaTitelTestmusic1AtIndex2() {
        when(mockRestTemplate.getForObject("http://customer-service/api/customer/" + 1, Customer.class))
                .thenReturn(mockCustomer);

        when(mockCustomer.getMediaInteractions()).thenReturn(mediaInteractionsList);

        Media[] mediaArraySortedByTimesListenedTo = { media3,media2, media1};

        when(mockRestTemplate.postForObject("http://Media-Service/api/media/getlistofmediadtofromlistofid", Arrays.asList(2, 1, 3), Media[].class))
                .thenReturn(mediaArraySortedByTimesListenedTo);

        List<Media> sortedListOfMedia = edufyUserService.getMostPlayedMediaForUserById(1);
        assertEquals("testmusic1",sortedListOfMedia.get(2).getTitle());
//        assertEquals(3, sortedListOfMedia.get(0).getId());
//        assertEquals(1, sortedListOfMedia.get(1).getId());
//        assertEquals(2, sortedListOfMedia.get(2).getId());
    }

    @Test
    void getMostPlayedMediaForUserById_shouldCreateListOfIdOrdered2_1_3() {

        int userId = 1;

        MediaInteractions mediaInteractions1 = new MediaInteractions(1, 1, "liked", 5); // Media ID 1, 5 listens
        MediaInteractions mediaInteractions2 = new MediaInteractions(2, 2, "liked", 10); // Media ID 2, 10 listens
        MediaInteractions mediaInteractions3 = new MediaInteractions(3, 3, "liked", 3); // Media ID 3, 3 listens
        List<MediaInteractions> mediaInteractionsList = Arrays.asList(mediaInteractions1, mediaInteractions2, mediaInteractions3);

        when(mockCustomer.getMediaInteractions()).thenReturn(mediaInteractionsList);
        when(mockRestTemplate.getForObject("http://customer-service/api/customer/" + userId, Customer.class)).thenReturn(mockCustomer);

        Media[] mediaArray = {media1, media3, media2};

        when(mockRestTemplate.postForObject(any(String.class), any(List.class), any(Class.class))).thenReturn(mediaArray);

        edufyUserService.getMostPlayedMediaForUserById(userId);

        //Få fram den sorterade listan med mediaIdn
        ArgumentCaptor<List<Integer>> captor = ArgumentCaptor.forClass(List.class);
        verify(mockRestTemplate).postForObject(
                Mockito.eq("http://Media-Service/api/media/getlistofmediadtofromlistofid"),
                captor.capture(),
                Mockito.eq(Media[].class)
        );

        List<Integer> capturedListOfIds = captor.getValue();
        // Kollar att lista med mediaId ifrån metoden är samma som den faktiska ordningen som det skulle vara
        assertEquals(Arrays.asList(2, 1, 3), capturedListOfIds); // Media ID 2 (10 listens), 1 (5 listens), 3 (3 listens)
    }

    //Validation of customerId
    @Test
    void rateMedia_shouldThrowExceptionForInvalidCustomerId() {
       IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            edufyUserService.rateMedia(0,1,"like");
        });

        assertEquals("customerId must be greater than 0", exception.getMessage());
    }

    //If customer not found
    @Test
    void rateMedia_shouldThrowExceptionWhenCustomerNotFound() {
        when(mockRestTemplate.getForObject(any(), eq(Customer.class))).thenReturn(null);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            edufyUserService.rateMedia(1,1,"like");
        });

        assertEquals("Error while processing media interaction",exception.getMessage());
    }

    //If media was not found
    @Test
    void rateMedia_shouldThrowExceptionWhenMediaNotFound() {

        int customerId = 1;
        int mediaId = 1;
        String likeStatus = "like";
        String getMediaURL = "http://media-service/api/media/1";

        Customer mockCustomer = new Customer();
        mockCustomer.setCustomerId(customerId);
        mockCustomer.setMediaInteractions(new ArrayList<>());

        // Mock the RestTemplate to return a non-null customer but null for media
        when(mockRestTemplate.getForObject(eq("http://customer-service/api/customer/1"), eq(Customer.class))).thenReturn(mockCustomer);
        when(mockRestTemplate.getForObject(eq(getMediaURL), eq(Media.class))).thenReturn(null);

        assertThrows(RuntimeException.class, () -> {
            edufyUserService.rateMedia(customerId, mediaId, likeStatus);
        }, "Media not found");
    }

    //Update of existing mediaInteraction
    @Test
    void rateMedia_shouldUpdateExistingMediaInteraction() {
        Customer mockCustomer = new Customer();
        mockCustomer.setCustomerId(1);
        List<MediaInteractions> mediaInteractionsList = new ArrayList<>(); // Mutable list
        mockCustomer.setMediaInteractions(mediaInteractionsList);

        MediaInteractions existingInteraction = new MediaInteractions();
        existingInteraction.setMediaId(1);
        existingInteraction.setLikeStatus("like");
        existingInteraction.setTimesListenedTo(10);
        mediaInteractionsList.add(existingInteraction);

        Media mockMedia = new Media();
        mockMedia.setId(1);
        mockMedia.setMediaType("Music");
        mockMedia.setTitle("Test Music");
        mockMedia.setURL("http://testurl.com");
        mockMedia.setReleaseDate(LocalDate.of(2020, 5, 15));
        mockMedia.setArtists(Collections.emptyList());
        mockMedia.setAlbums(Collections.emptyList());
        mockMedia.setGenres(Collections.emptyList());

        when(mockRestTemplate.getForObject("http://customer-service/api/customer/1", Customer.class)).thenReturn(mockCustomer);
        when(mockRestTemplate.getForObject("http://media-service/api/media/1", Media.class)).thenReturn(mockMedia);

        MediaInteractions updatedInteraction = new MediaInteractions();
        updatedInteraction.setMediaId(1);
        updatedInteraction.setLikeStatus("like");
        updatedInteraction.setTimesListenedTo(10);

        edufyUserService.rateMedia(1, 1, "like");

        verify(mockRestTemplate).put(eq("http://customer-service/api/customer/updatecustomer/1"), any(Customer.class));
    }

    //Create a new mediaInteraction
    @Test
    void rateMedia_shouldCreateNewMediaInteraction() {
        Customer mockCustomer = new Customer();
        Media mockMedia = new Media();
        mockMedia.setId(1);

        // Initialize mediaInteractions-list as empty but not null
        mockCustomer.setMediaInteractions(new ArrayList<>());

        MediaInteractions newInteraction = new MediaInteractions();
        newInteraction.setMediaId(1);
        newInteraction.setLikeStatus("like");

        when(mockRestTemplate.getForObject(anyString(), eq(Customer.class))).thenReturn(mockCustomer);
        when(mockRestTemplate.getForObject(anyString(), eq(Media.class))).thenReturn(mockMedia);
        when(mockRestTemplate.postForEntity(anyString(), any(MediaInteractions.class), eq(MediaInteractions.class)))
                .thenReturn(new ResponseEntity<>(newInteraction, HttpStatus.CREATED));

        MediaInteractions result = edufyUserService.rateMedia(1, 1, "like");

        assertNotNull(result);
        assertEquals("like", result.getLikeStatus());
        assertEquals(1, result.getMediaId());

        //Verifies that the correct URL and object are passed to the postForEntity method
        verify(mockRestTemplate).postForEntity(eq("http://customer-service/api/customer/addmediainteractions"), any(MediaInteractions.class), eq(MediaInteractions.class));
        verify(mockRestTemplate).put(eq("http://customer-service/api/customer/updatecustomer/1"), eq(mockCustomer));
    }

    @Test
    void testGetRecommendedMedia_FillsToExpectedNumber() {
        when(mockRestTemplate.getForObject(eq("http://customer-service/api/customer/1"), eq(Customer.class)))
                .thenReturn(customerTest);

        List<Genre> genres1 = Collections.singletonList(new Genre(1, "Rock"));
        List<Genre> genres2 = Collections.singletonList(new Genre(2, "Pop"));
        List<Genre> genres3 = Collections.singletonList(new Genre(3, "Jazz"));
        List<Genre> genresOther = Collections.singletonList(new Genre(4, "Classical"));

        Media media4 = new Media(4, "music4", "testmusic4", "www.TestMusic4.com", LocalDate.now());
        media4.setGenres(genresOther);
        Media media5 = new Media(5, "music5", "testmusic5", "www.TestMusic5.com", LocalDate.now());
        media5.setGenres(genresOther);

        media1.setGenres(genres1);
        media2.setGenres(genres2);
        media3.setGenres(genres3);
        media4.setGenres(genresOther);
        media5.setGenres(genresOther);

        List<Media> mockMediaList = Arrays.asList(media1, media2, media3, media4, media5);

        when(mockRestTemplate.getForObject(eq("http://Media-Service/api/media/getallmediadto"), eq(Media[].class)))
                .thenReturn(mockMediaList.toArray(new Media[0]));

        List<Media> recommendations = edufyUserService.getRecommendedMedia(1);

        assertEquals(5, recommendations.size());  // Kontrollera att metoden returnerar exakt så många media som vi mockat
        assertTrue(recommendations.containsAll(mockMediaList));  // Kontrollera att de mockade mediaobjekten finns med
    }



}

