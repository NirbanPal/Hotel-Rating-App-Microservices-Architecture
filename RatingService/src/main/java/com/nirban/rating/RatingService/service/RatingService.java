package com.nirban.rating.RatingService.service;

import com.nirban.rating.RatingService.dto.responseDto.RatingDto;
import com.nirban.rating.RatingService.entities.Rating;

import java.util.List;

public interface RatingService {

    //create
    Rating create(RatingDto rating);


    //get all ratings
    List<Rating> getRatings();

    //get all by UserId
    List<Rating> getRatingByUserId(String userId);

    //get all by hotel
    List<Rating> getRatingByHotelId(String hotelId);
}
