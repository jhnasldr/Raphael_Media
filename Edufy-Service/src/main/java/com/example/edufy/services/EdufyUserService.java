package com.example.edufy.services;

import com.example.edufy.VO.*;
//import com.example.edufy.repositories.EdufyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EdufyUserService implements EdufyServiceInterface {
//
//    @Autowired
//    private EdufyUserRepository edufyUserRepository;
    @Autowired
    private RestTemplate restTemplate;



    //Test för forskning, det saknas enhetstestning för den här mm
    public Customer getCustomerVO() {
        //Har man med loadbalanced letar den inom sina services och man kan inte köra med localhost utan måste använda servicenamnet
        //    Customer customerVO = restTemplate.getForObject("http://127.0.0.1:6060/api/customer/1", Customer.class);
        //    Customer customerVO = restTemplate.getForObject("localhost:6060/api/customer/1", Customer.class);
        Customer customerVO = restTemplate.getForObject("http://customer-service/api/customer/1", Customer.class);

        // restTemplate och getForObject för att köra get, verkar finnas metoder för alla crud operationer med restTemplate
        return customerVO;
    }

    public Media getMediaVO() {
        //Har man med loadbalanced letar den inom sina services och man kan inte köra med localhost utan måste använda servicenamnet
        //    Customer customerVO = restTemplate.getForObject("http://127.0.0.1:6060/api/customer/1", Customer.class);
        //    Customer customerVO = restTemplate.getForObject("localhost:6060/api/customer/1", Customer.class);
        Media mediaVO = restTemplate.getForObject("http://media-service/api/media/1", Media.class);

        // restTemplate och getForObject för att köra get, verkar finnas metoder för alla crud operationer med restTemplate
        return mediaVO;
    }

    public Customer putCustomerVO() {
        //Har man med loadbalanced letar den inom sina services och man kan inte köra med localhost utan måste använda servicenamnet
        //    Customer customerVO = restTemplate.getForObject("http://127.0.0.1:6060/api/customer/1", Customer.class);
        //    Customer customerVO = restTemplate.getForObject("localhost:6060/api/customer/1", Customer.class);
        Customer customerVO = getCustomerVO();
        customerVO.setUserName("hej");
        customerVO.setEmailAdress("hej.com");
        restTemplate.put("http://customer-service/api/customer/updatecustomer/1", customerVO);

        // restTemplate och getForObject för att köra get, verkar finnas metoder för alla crud operationer med restTemplate
        return customerVO;
    }

    public MediaInteractions  postMediaInteractionsVO() {
        //Har man med loadbalanced letar den inom sina services och man kan inte köra med localhost utan måste använda servicenamnet
        //    Customer customerVO = restTemplate.getForObject("http://127.0.0.1:6060/api/customer/1", Customer.class);
        //    Customer customerVO = restTemplate.getForObject("localhost:6060/api/customer/1", Customer.class);
        MediaInteractions mediaInteractions = new MediaInteractions();
        mediaInteractions.setMediaId(100);
        mediaInteractions.setTimesListenedTo(1);
        mediaInteractions.setLikeStatus("like");
        restTemplate.postForEntity("http://customer-service/api/customer/addmediainteractions", mediaInteractions, MediaInteractions.class);

        // restTemplate och getForObject för att köra get, verkar finnas metoder för alla crud operationer med restTemplate
        return mediaInteractions;
    }

    @Override
    public Media playAndUpdateListedToInCustomer(int idCustomer, int idMedia){
        String customerGetURL = "http://customer-service/api/customer/" + idCustomer;
        String mediaGetURL = "http://media-service/api/media/" + idMedia;
        String customerPutURl = "http://customer-service/api/customer/updatecustomer/" + idCustomer;
        String mediaInteractionsPostURl = "http://customer-service/api/customer/addmediainteractions";

        Customer customerVO = restTemplate.getForObject(customerGetURL, Customer.class);
        Media mediaVO = restTemplate.getForObject(mediaGetURL,Media.class);

        customerVO.setUserName("kevin");
        customerVO.setEmailAdress("new kevin");

        for (MediaInteractions m: customerVO.getMediaInteractions()) {
            if (m.getMediaId() == mediaVO.getId()){
                m.increasePlayCount();
                restTemplate.put(customerPutURl,customerVO);
                return mediaVO;
            }
        }

        MediaInteractions mediaInteractionsVO = new MediaInteractions("empty",0,customerVO);


        mediaInteractionsVO.setMediaInteractionId(customerVO.getMediaInteractions().size()+1);
        mediaInteractionsVO.setMediaId(mediaVO.getId());
        mediaInteractionsVO.increasePlayCount();
        //restTemplate.postForEntity(mediaInteractionsPostURl,mediaInteractionsVO, MediaInteractions.class);
        customerVO.getMediaInteractions().add(mediaInteractionsVO);

        restTemplate.put(customerPutURl,customerVO);
        return mediaVO;
    }

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Customer getCustomerData(int customerId) {
        String url = "http://customer-service/api/customer/" + customerId;
        return restTemplate.getForObject(url, Customer.class);
    }

    /*public List<MediaDTO> getAllMediaDTO() {
        String url = "http://media-service/api/media/getallmediadto";
        MediaDTO[] mediaArray = restTemplate.getForObject(url, MediaDTO[].class);
        return Arrays.asList(mediaArray);
    }*/
    public List<Media> getAllMedia() {
        String url = "http://media-service/api/media/getallmedia";
        Media[] mediaArray = restTemplate.getForObject(url, Media[].class);
        return Arrays.asList(mediaArray);
    }

    public List<Media> getAllMediaDTO() {
        List<Media> mediaDTO;
        mediaDTO = Arrays.asList(Objects.requireNonNull(restTemplate.getForObject("http://Media-Service/api/media/getallmediadto", Media[].class)));
        return mediaDTO;
    }

    public Media getMediaById(int mediaId) {
        String url = "http://media-service/api/media/" + mediaId;
        Media media = restTemplate.getForObject(url, Media.class);

        if (media == null) {
            // Logga felet om media inte hittas
            System.err.println("Media with id " + mediaId + " not found.");
            throw new RuntimeException("Media not found for id: " + mediaId);
        }

        System.out.println("Media fetched: " + media.getTitle());
        return media;
    }

    public List<Media> getRecommendedMedia(int customerId) {
        // Hämta kundens interaktioner
        Customer customer = getCustomerData(customerId);
        List<Media> recommendedMediaDTOs = new ArrayList<>();

        // Skapa en set för att undvika dubbletter
        Set<Integer> usedMediaIds = new HashSet<>();

        // Hämta alla MediaDTO från Media-tjänsten
        List<Media> allMediaDTO = getAllMediaDTO();

        // För varje media-interaktion, hämta motsvarande MediaDTO från listan med alla media
        for (MediaInteractions interaction : customer.getMediaInteractions()) {
            Media mediaDTO = allMediaDTO.stream()
                    .filter(media -> media.getId() == interaction.getMediaId())
                    .findFirst()
                    .orElse(null);

            if (mediaDTO != null && !usedMediaIds.contains(mediaDTO.getId())) {
                recommendedMediaDTOs.add(mediaDTO);
                usedMediaIds.add(mediaDTO.getId());
            }
        }

        // Fyll upp listan med fler media från andra genrer om det är färre än 10
        if (recommendedMediaDTOs.size() < 10) {
            for (Media mediaDTO : allMediaDTO) {
                if (!usedMediaIds.contains(mediaDTO.getId())) {
                    recommendedMediaDTOs.add(mediaDTO);
                    usedMediaIds.add(mediaDTO.getId());

                    if (recommendedMediaDTOs.size() == 10) {
                        break;
                    }
                }
            }
        }

        return recommendedMediaDTOs;
    }

    /*public List<MediaDTO> getRecommendedMedia(int customerId) { //Senaste som funkar med lokalt DTO!
        // Hämta kundens interaktioner
        Customer customer = getCustomerData(customerId);
        List<MediaDTO> recommendedMediaDTOs = new ArrayList<>();

        // Skapa en set för att undvika dubbletter
        Set<Integer> usedMediaIds = new HashSet<>();

        // Hämta alla media
        List<Media> allMedia = getAllMedia();

        // För varje media-interaktion, hämta motsvarande media och mappa till MediaDTO
        for (MediaInteractions interaction : customer.getMediaInteractions()) {
            Media media = getMediaById(interaction.getMediaId());
            if (!usedMediaIds.contains(media.getId())) {
                MediaDTO mediaDTO = new MediaDTO();
                mediaDTO.setId(media.getId());
                mediaDTO.setTitle(media.getTitle());
                mediaDTO.setMediaType(media.getMediaType());

                // Extrahera första genren och artistens namn som strängar
                String genre = media.getGenres() != null && !media.getGenres().isEmpty()
                        ? media.getGenres().get(0).getGenre() : "Unknown Genre";
                mediaDTO.setGenre(genre);

                String artist = media.getArtists() != null && !media.getArtists().isEmpty()
                        ? media.getArtists().get(0).getArtistName() : "Unknown Artist";
                mediaDTO.setArtist(artist);

                recommendedMediaDTOs.add(mediaDTO);
                usedMediaIds.add(media.getId());
            }
        }

        // Fyll upp listan med fler media från andra genrer om det är färre än 10
        if (recommendedMediaDTOs.size() < 10) {
            for (Media media : allMedia) {
                if (!usedMediaIds.contains(media.getId())) {
                    MediaDTO mediaDTO = new MediaDTO();
                    mediaDTO.setId(media.getId());
                    mediaDTO.setTitle(media.getTitle());
                    mediaDTO.setMediaType(media.getMediaType());

                    // Extrahera första genren och artistens namn som strängar
                    String genre = media.getGenres() != null && !media.getGenres().isEmpty()
                            ? media.getGenres().get(0).getGenre() : "Unknown Genre";
                    mediaDTO.setGenre(genre);

                    String artist = media.getArtists() != null && !media.getArtists().isEmpty()
                            ? media.getArtists().get(0).getArtistName() : "Unknown Artist";
                    mediaDTO.setArtist(artist);

                    recommendedMediaDTOs.add(mediaDTO);
                    usedMediaIds.add(media.getId());

                    if (recommendedMediaDTOs.size() == 10) {
                        break;
                    }
                }
            }
        }

        return recommendedMediaDTOs;
    }*/


    // Generera rekommendationer genom att kombinera data från Customer och Media-tjänsterna
    /*public List<Media> getRecommendedMedia(int customerId) {
        // Hämta kundens data inklusive media-interaktioner
        Customer customer = getCustomerData(customerId);
        List<Media> recommendedMedia = new ArrayList<>();

        // För varje media-interaktion, hämta motsvarande media från Media-tjänsten
        for (MediaInteractions interaction : customer.getMediaInteractions()) {
            Media media = getMediaById(interaction.getMediaId());

            // Om användaren har markerat media som "like" eller lyssnat på det flera gånger
            if (!"dislike".equals(interaction.getLikeStatus()) && interaction.getTimesListenedTo() > 0) {
                recommendedMedia.add(media);
            }
        }

        return recommendedMedia;
    }*/

    // Generera topp 10 rekommendationer för en kund
    /*public List<Media> getRecommendedMedia(Customer customer) {
        List<Genre> top3Genres = getTop3Genres(customer);
        List<Media> allMedia = getAllMedia();

        // Hämta media som användaren redan har lyssnat på eller markerat som "dislike"
        Set<Integer> listenedOrDislikedMediaIds = customer.getMediaInteractions().stream()
                .filter(interaction -> "dislike".equals(interaction.getLikeStatus()) || interaction.getTimesListenedTo() > 0)
                .map(MediaInteractions::getMediaId)
                .collect(Collectors.toSet());

        // 80% rekommendationer från användarens topp 3 genrer
        List<Media> topGenreRecommendations = allMedia.stream()
                .filter(media -> !listenedOrDislikedMediaIds.contains(media.getId()))  // Filtrera bort media som redan har lyssnats på
                .filter(media -> media.getGenres().stream().anyMatch(top3Genres::contains)) // Filtrera ut media med topp 3 genrer
                .limit(8) // 80% rekommendationer (8 av 10)
                .collect(Collectors.toList());

        // 20% rekommendationer från andra genrer
        List<Media> otherGenreRecommendations = allMedia.stream()
                .filter(media -> !listenedOrDislikedMediaIds.contains(media.getId()))  // Filtrera bort media som redan har lyssnats på
                .filter(media -> media.getGenres().stream().noneMatch(top3Genres::contains)) // Filtrera bort media från topp 3 genrer
                .limit(2) // 20% rekommendationer (2 av 10)
                .collect(Collectors.toList());

        // Kombinera båda listorna
        topGenreRecommendations.addAll(otherGenreRecommendations);
        return topGenreRecommendations;
    }

    // Hämta användarens topp 3 genrer
    public List<Genre> getTop3Genres(Customer customer) {
        Map<Genre, Integer> genreCount = new HashMap<>();

        for (MediaInteractions interaction : customer.getMediaInteractions()) {
            if (interaction.getTimesListenedTo() > 0) {
                Media media = getMediaById(interaction.getMediaId());

                if (media.getGenres() != null && !media.getGenres().isEmpty()) {
                    for (Genre genre : media.getGenres()) {
                        genreCount.put(genre, genreCount.getOrDefault(genre, 0) + interaction.getTimesListenedTo());
                    }
                }
            }
        }

        return genreCount.entrySet().stream()
                .sorted((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue())) // Sortera genrer baserat på antal lyssningar
                .limit(3) // Returnera topp 3 genrer
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }*/
}

