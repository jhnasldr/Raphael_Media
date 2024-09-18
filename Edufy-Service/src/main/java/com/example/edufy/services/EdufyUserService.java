package com.example.edufy.services;

import com.example.edufy.DTOs.MediaResponseDTO;
import com.example.edufy.VO.*;
import com.example.edufy.VO.Media;
import com.example.edufy.VO.MediaInteractions;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EdufyUserService implements EdufyServiceInterface {

    @Autowired
    private RestTemplate restTemplate;

    Logger logger = Logger.getLogger(EdufyUserService.class);

    //Test för forskning, det saknas enhetstestning för den här mm
//    public Customer getCustomerVO() {
//        //Har man med loadbalanced letar den inom sina services och man kan inte köra med localhost utan måste använda servicenamnet
//        //    Customer customerVO = restTemplate.getForObject("http://127.0.0.1:6060/api/customer/1", Customer.class);
//        //    Customer customerVO = restTemplate.getForObject("localhost:6060/api/customer/1", Customer.class);
//        Customer customerVO = restTemplate.getForObject("http://customer-service/api/customer/1", Customer.class);
//
//        // restTemplate och getForObject för att köra get, verkar finnas metoder för alla crud operationer med restTemplate
//        return customerVO;
//    }

    @Override
    public Media playAndUpdateListedToInCustomer(int idCustomer, int idMedia) {
        String customerGetURL = "http://customer-service/api/customer/" + idCustomer;
        String mediaGetURL = "http://media-service/api/media/" + idMedia;
        String customerPutURl = "http://customer-service/api/customer/updatecustomer/" + idCustomer;

        Customer customerVO = restTemplate.getForObject(customerGetURL, Customer.class);
        Media mediaVO = restTemplate.getForObject(mediaGetURL, Media.class);

        for (MediaInteractions m : customerVO.getMediaInteractions()) {
            if (m.getMediaId() == mediaVO.getId()) {
                m.increasePlayCount();
                restTemplate.put(customerPutURl, customerVO);
                logger.log(Level.WARN, "Customer id: " + idCustomer + " played media with id: " + idMedia);
                return mediaVO;
            }
        }

        MediaInteractions mediaInteractionsVO = new MediaInteractions();
        mediaInteractionsVO.setLikeStatus("empty");
        mediaInteractionsVO.increasePlayCount();
        mediaInteractionsVO.setMediaId(mediaVO.getId());
        mediaInteractionsVO.setCustomer(customerVO);
        customerVO.getMediaInteractions().add(mediaInteractionsVO);
        System.out.println(customerVO.getMediaInteractions().size());
        restTemplate.put(customerPutURl, customerVO);

        return mediaVO;
    }

    @Override
    public MediaInteractions rateMedia(int customerId, int mediaId, String likeStatus) {
        if (customerId <= 0) {
            throw new IllegalArgumentException("customerId must be greater than 0");
        }

        String getCustomerURL = "http://customer-service/api/customer/" + customerId;
        String getMediaURL = "http://media-service/api/media/" + mediaId;
        String customerPutURl = "http://customer-service/api/customer/updatecustomer/" + customerId;
        String mediaInteractionsURL = "http://customer-service/api/customer/addmediainteractions";

        try {
            Customer customerVO = restTemplate.getForObject(getCustomerURL, Customer.class);
            if (customerVO == null) {
                throw new RuntimeException("Customer not found");
            }
            Media mediaVO = restTemplate.getForObject(getMediaURL, Media.class);
            if (mediaVO == null) {
                throw new RuntimeException("Media not found");
            }

            MediaInteractions existingInteraction = customerVO.getMediaInteractions().stream()
                    .filter(m -> m.getMediaId() == mediaVO.getId())
                    .findFirst()
                    .orElse(null);

            if (existingInteraction != null) {
                existingInteraction.setLikeStatus(likeStatus);
                restTemplate.put(customerPutURl, customerVO);
                return existingInteraction;
            } else {
                MediaInteractions newInteraction = new MediaInteractions("empty", 0, customerVO);
                newInteraction.setMediaId(mediaVO.getId());
                newInteraction.setLikeStatus(likeStatus);
                newInteraction.setCustomer(customerVO);

                customerVO.getMediaInteractions().add(newInteraction);

                restTemplate.postForEntity(mediaInteractionsURL, newInteraction, MediaInteractions.class);
                restTemplate.put(customerPutURl, customerVO);

                return newInteraction;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error while processing media interaction", e);
        }
    }

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<MediaResponseDTO> getMostPlayedMediaForUserById(int userId) {
        Customer customer;
        List<MediaInteractions> mediaInteractions;
        List<MediaInteractions> mediaInteractionsSortedByTimesListenedTo;
        List<Integer> idsOfMostPlayedMediaSorted;
        List<Media> mostPlayedMedia;
        List<MediaResponseDTO> mostPlayedMediaDTO;

        customer = restTemplate.getForObject("http://customer-service/api/customer/" + userId, Customer.class);

        mediaInteractions = customer.getMediaInteractions();

        mediaInteractionsSortedByTimesListenedTo = mediaInteractions.stream().sorted(Comparator.comparingInt(MediaInteractions::getTimesListenedTo).reversed()).limit(10).collect(Collectors.toList());


        idsOfMostPlayedMediaSorted = mediaInteractionsSortedByTimesListenedTo.stream()
                .map(MediaInteractions::getMediaId)
                .distinct()
                .collect(Collectors.toList());

        mostPlayedMedia = Arrays.stream(Objects.requireNonNull(restTemplate.postForObject(
                        "http://Media-Service/api/media/getlistofmediadtofromlistofid",
                        idsOfMostPlayedMediaSorted,
                        Media[].class)))
                .collect(Collectors.toList());

        mostPlayedMediaDTO = mostPlayedMedia.stream()
                .map(media -> new MediaResponseDTO(
                        media.getId(),
                        media.getMediaType(),
                        media.getTitle(),
                        media.getGenres() != null && !media.getGenres().isEmpty() ? media.getGenres().get(0).getGenre() : "Unknown Genre",
                        media.getArtists() != null && !media.getArtists().isEmpty() ? media.getArtists().get(0).getArtistName() : "Unknown Artist"
                ))
                .collect(Collectors.toList());

        return mostPlayedMediaDTO;
    }

    public Customer getCustomerData(int customerId) {
        String url = "http://customer-service/api/customer/" + customerId;
        return restTemplate.getForObject(url, Customer.class);
    }

    public List<Media> getAllMediaDTO() {
        List<Media> mediaDTO;
        mediaDTO = Arrays.asList(Objects.requireNonNull(restTemplate.getForObject("http://Media-Service/api/media/getallmediadto", Media[].class)));
        return mediaDTO;
    }

    @Override
    public List<MediaResponseDTO> getRecommendedMedia(int customerId) {
        // Hämta kundens interaktioner
        Customer customer = getCustomerData(customerId);
        List<Media> recommendedMedia = new ArrayList<>();
        // Skapa en set för att undvika dubbletter
        Set<Integer> usedMediaIds = new HashSet<>();
        // Hämta alla Media från Media-tjänsten
        List<Media> allMediaDTO = getAllMediaDTO();

        // Filtrera bort media som användaren markerat som "dislike" och media de redan lyssnat på
        Set<Integer> interactedMediaIds = customer.getMediaInteractions().stream()
                .filter(interaction -> interaction.getTimesListenedTo() > 0 || "dislike".equalsIgnoreCase(interaction.getLikeStatus()))
                .map(MediaInteractions::getMediaId)
                .collect(Collectors.toSet());

        // Hämta användarens topp 3 genrer baserat på timesListenedTo
        Map<Genre, Integer> genreListenCount = new HashMap<>();
        for (MediaInteractions interaction : customer.getMediaInteractions()) {
            Media mediaDTO = allMediaDTO.stream()
                    .filter(media -> media.getId() == interaction.getMediaId())
                    .findFirst()
                    .orElse(null);

            if (mediaDTO != null && !interactedMediaIds.contains(mediaDTO.getId())) {
                for (Genre genre : mediaDTO.getGenres()) {
                    genreListenCount.put(genre, genreListenCount.getOrDefault(genre, 0) + interaction.getTimesListenedTo());
                }
            }
        }
        // Sortera genrer efter antal lyssningar och hämta topp 3
        List<Genre> topGenres = genreListenCount.entrySet().stream()
                .sorted(Map.Entry.<Genre, Integer>comparingByValue().reversed())
                .limit(3)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        // Hämta rekommendationer från topp 3 genrer
        List<Media> topGenreRecommendations = allMediaDTO.stream()
                .filter(media -> !interactedMediaIds.contains(media.getId()))  // Uteslut media som användaren inte gillar och redan lyssnat på
                .filter(media -> media.getGenres().stream().anyMatch(topGenres::contains))  // Filtrera för topp 3 genrer
                .filter(media -> !usedMediaIds.contains(media.getId()))  // Undvik dubbletter
                .limit(8)  // Försök att rekommendera 80% från topp 3 genrer (8 av 10)
                .collect(Collectors.toList());

        recommendedMedia.addAll(topGenreRecommendations);
        topGenreRecommendations.forEach(media -> usedMediaIds.add(media.getId()));  // Lägg till i usedMediaIds

        // Kolla om vi har mindre än 10 rekommendationer
        int remainingSlots = 10 - recommendedMedia.size();

        // Om det finns färre än 8 rekommendationer från toppgenrerna, fyll på med andra genrer tills vi når 10
        if (remainingSlots > 0) {
            List<Media> otherGenreRecommendations = allMediaDTO.stream()
                    .filter(media -> !interactedMediaIds.contains(media.getId()))  // Uteslut media som användaren inte gillar och redan lyssnat på
                    .filter(media -> media.getGenres().stream().noneMatch(topGenres::contains))  // Filtrera bort topp 3 genrer
                    .filter(media -> !usedMediaIds.contains(media.getId()))  // Undvik dubbletter
                    .collect(Collectors.toList());

            // Slumpa ordningen på media från andra genrer
            Collections.shuffle(otherGenreRecommendations);

            // Lägg till upp till de återstående platserna
            recommendedMedia.addAll(otherGenreRecommendations.stream()
                    .limit(remainingSlots)
                    .collect(Collectors.toList()));
        }
        return recommendedMedia.stream()
                .map(media -> new MediaResponseDTO(
                        media.getId(),
                        media.getMediaType(),
                        media.getTitle(),
                        media.getGenres() != null && !media.getGenres().isEmpty() ? media.getGenres().get(0).getGenre() : "Unknown Genre",
                        media.getArtists() != null && !media.getArtists().isEmpty() ? media.getArtists().get(0).getArtistName() : "Unknown Artist"
                ))
                .collect(Collectors.toList());
    }

}

