package com.example.edufy.services;

import com.example.edufy.DTOs.MediaResponseDTO;
import com.example.edufy.VO.*;
import com.example.edufy.VO.Media;
import com.example.edufy.VO.MediaInteractions;
import com.example.edufy.exceptions.ResourceNotFoundException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EdufyUserService implements EdufyServiceInterface {
    @Autowired
    private RestTemplate restTemplate;

    Logger logger = Logger.getLogger(EdufyUserService.class);

    @Override
    public Media playAndUpdateListedToInCustomer(int idCustomer, int idMedia) {
        Customer customerVO = getCustomerData(idCustomer);
        Media mediaVO = getMedia(idMedia);

        for (MediaInteractions m : customerVO.getMediaInteractions()) {
            if (m.getMediaId() == mediaVO.getId()) {
                m.increasePlayCount();
                updateCustomer(idCustomer, customerVO);
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
        updateCustomer(idCustomer, customerVO);

        return mediaVO;
    }

    @Override
    public MediaInteractions rateMedia(int customerId, int mediaId, String likeStatus) {
        Customer customerVO = getCustomerData(customerId);
        Media mediaVO = getMedia(mediaId);

        MediaInteractions existingInteraction = customerVO.getMediaInteractions().stream()
                .filter(m -> m.getMediaId() == mediaVO.getId())
                .findFirst()
                .orElse(null);

        if (existingInteraction != null) {
            existingInteraction.setLikeStatus(likeStatus);
            updateCustomer(customerId, customerVO);
            return existingInteraction;
        } else {
            MediaInteractions newInteraction = new MediaInteractions("empty", 0, customerVO);
            newInteraction.setMediaId(mediaVO.getId());
            newInteraction.setLikeStatus(likeStatus);
            newInteraction.setCustomer(customerVO);

            customerVO.getMediaInteractions().add(newInteraction);

            createNewMediaInteraction(newInteraction);
            updateCustomer(customerId, customerVO);
            return newInteraction;
        }
    }

    @Override
    public List<MediaResponseDTO> getMostPlayedMediaForUserById(int userId, int listSize) {
        Customer customer = getCustomerData(userId);
        List<MediaInteractions> mediaInteractionsSortedByTimesListenedTo;
        List<Integer> idsOfMostPlayedMediaSorted;
        List<Media> mostPlayedMedia;
        List<MediaResponseDTO> mostPlayedMediaDTO;


        mediaInteractionsSortedByTimesListenedTo = customer.getMediaInteractions().stream().sorted(Comparator.comparingInt(MediaInteractions::getTimesListenedTo).reversed()).limit(listSize).collect(Collectors.toList());

        idsOfMostPlayedMediaSorted = mediaInteractionsSortedByTimesListenedTo.stream()
                .map(MediaInteractions::getMediaId)
                .distinct()
                .collect(Collectors.toList());

        mostPlayedMedia = getListOfMedia(idsOfMostPlayedMediaSorted);

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

    @Override
    public List<MediaResponseDTO> getRecommendedMedia(int customerId) {
        Customer customer = getCustomerData(customerId);
        List<Media> recommendedMedia = new ArrayList<>();
        Set<Integer> usedMediaIds = new HashSet<>();
        List<Media> allMediaDTO = getAllMediaDTO();

        // Filtrera bort media som användaren markerat som "dislike" och media de redan lyssnat på
        Set<Integer> interactedMediaIds = customer.getMediaInteractions().stream()
                .filter(interaction -> interaction.getTimesListenedTo() > 0 || "dislike".equalsIgnoreCase(interaction.getLikeStatus()))
                .map(MediaInteractions::getMediaId)
                .collect(Collectors.toSet());

        // Hämta användarens topp 3 genrer baserat på timesListenedTo
        Map<String, Integer> genreListenCount = new HashMap<>();
        for (MediaInteractions interaction : customer.getMediaInteractions()) {
            Media mediaDTO = allMediaDTO.stream()
                    .filter(media -> media.getId() == interaction.getMediaId())
                    .findFirst()
                    .orElse(null);

            if (mediaDTO != null) {
                for (Genre genre : mediaDTO.getGenres()) {
                    String genreName = genre.getGenre();
                    genreListenCount.put(genreName, genreListenCount.getOrDefault(genreName, 0) + interaction.getTimesListenedTo());
                }
            }
        }

        // Sortera genrer efter antal lyssningar och hämta topp 3
        List<String> topGenres = genreListenCount.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(3)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        // Rekommendera media från topp 3 genrer
        for (String genre : topGenres) {
            List<Media> genreRecommendations = allMediaDTO.stream()
                    .filter(media -> !interactedMediaIds.contains(media.getId()))
                    .filter(media -> media.getGenres().stream().anyMatch(g -> g.getGenre().equalsIgnoreCase(genre)))
                    .filter(media -> !usedMediaIds.contains(media.getId()))
                    .collect(Collectors.toList());

            for (Media media : genreRecommendations) {
                if (recommendedMedia.size() < 8) {
                    recommendedMedia.add(media);
                    usedMediaIds.add(media.getId());
                } else {
                    break;
                }
            }
            if (recommendedMedia.size() >= 8) {
                break;
            }
        }

        int remainingSlots = 10 - recommendedMedia.size();

        if (remainingSlots > 0) {
            List<Media> otherGenreRecommendations = allMediaDTO.stream()
                    .filter(media -> !interactedMediaIds.contains(media.getId()))
                    .filter(media -> media.getGenres().stream().noneMatch(g -> topGenres.contains(g.getGenre())))
                    .filter(media -> !usedMediaIds.contains(media.getId()))
                    .collect(Collectors.toList());

            Collections.shuffle(otherGenreRecommendations);

            for (Media media : otherGenreRecommendations.stream().limit(remainingSlots).collect(Collectors.toList())) {
                recommendedMedia.add(media);
                usedMediaIds.add(media.getId());
            }
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

    public Customer getCustomerData(int customerId) {
        String url = "http://customer-service/api/customer/" + customerId;

        try {
            return restTemplate.getForObject(url, Customer.class);
        } catch (HttpClientErrorException.NotFound e) {
            throw new ResourceNotFoundException("customer", "id", customerId);
        } catch (IllegalStateException e) {
            throw new RuntimeException("Failed to connect to customer service");
        }
    }

    public Media getMedia(int mediaId) {
        String mediaGetURL = "http://media-service/api/media/" + mediaId;

        try {
            return restTemplate.getForObject(mediaGetURL, Media.class);
        } catch (HttpClientErrorException.NotFound e) {
            throw new ResourceNotFoundException("Media", "id", mediaId);
        } catch (IllegalStateException e) {
            throw new RuntimeException("Failed to connect to media service");
        }
    }

    public void updateCustomer(int customerId, Customer customerVO) {
        String customerPutURl = "http://customer-service/api/customer/updatecustomer/" + customerId;

        try {
            restTemplate.put(customerPutURl, customerVO);
        } catch (HttpClientErrorException.NotFound e) {
            throw new ResourceNotFoundException("customer", "id", customerId);
        } catch (IllegalStateException e) {
            throw new RuntimeException("Failed to connect to customer service");
        }
    }

    public void createNewMediaInteraction(MediaInteractions newMediaInteraction) {
        String mediaInteractionsURL = "http://customer-service/api/customer/addmediainteractions";

        try {
            restTemplate.postForEntity(mediaInteractionsURL, newMediaInteraction, MediaInteractions.class);
        } catch (IllegalStateException e) {
            throw new RuntimeException("Failed to create new media interaction in customer service");
        }
    }

    public List<Media> getListOfMedia(List<Integer> listOfMediaId) {
        String getMediaListURL = "http://Media-Service/api/media/getlistofmediadtofromlistofid";

        try {
            return Arrays.asList(Objects.requireNonNull(restTemplate.postForObject(getMediaListURL, listOfMediaId, Media[].class)));
        } catch (Exception e) {
            throw new RuntimeException("Failed to get list of media from media service");
        }
    }

    public List<Media> getAllMediaDTO() {
        String getAllMediaDTOURL = "http://Media-Service/api/media/getallmediadto";

        try {
            return Arrays.asList(Objects.requireNonNull(restTemplate.getForObject(getAllMediaDTOURL, Media[].class)));
        } catch (Exception e) {
            throw new RuntimeException("Failed to get list of media from media service");
        }
    }

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
}


