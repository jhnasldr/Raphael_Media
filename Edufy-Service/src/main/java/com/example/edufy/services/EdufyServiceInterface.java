package com.example.edufy.services;

import com.example.edufy.DTOs.MediaResponseDTO;
import com.example.edufy.VO.Media;
import com.example.edufy.VO.MediaInteractions;

import java.util.List;

public interface EdufyServiceInterface {

    Media playAndUpdateListedToInCustomer(int idCustomer, int idMedia);

    MediaInteractions rateMedia(int customerId, int mediaId, String likeStatus);

    List<MediaResponseDTO> getMostPlayedMediaForUserById(int userId);

    List<MediaResponseDTO> getRecommendedMedia(int customerId);
}
