package com.example.edufy.services;

import com.example.edufy.VO.Customer;
import com.example.edufy.VO.Media;
import com.example.edufy.VO.MediaInteractions;

public interface EdufyServiceInterface {

    Media playAndUpdateListedToInCustomer(int idCustomer, int idMedia);

    MediaInteractions rateMedia(int customerId, int mediaId, String likeStatus);
}
