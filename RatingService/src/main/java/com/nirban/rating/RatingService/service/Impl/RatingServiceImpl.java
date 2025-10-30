package com.nirban.rating.RatingService.service.Impl;

import com.nirban.rating.RatingService.dto.responseDto.RatingDto;
import com.nirban.rating.RatingService.repository.RatingRepository;
import com.nirban.rating.RatingService.service.RatingService;
import com.nirban.rating.RatingService.entities.Rating;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    private static final Logger logger = LoggerFactory.getLogger(RatingServiceImpl.class);

    @Override
    public Rating create(RatingDto ratingDto) {
        //In mongodb as id are auto generated you dont have to create id separately.
//        String ratingId = UUID.randomUUID().toString();
//        rating.setRatingId(ratingId);

        Rating rating = new Rating();
        rating.setUserId(ratingDto.getUserId());
        rating.setHotelId(ratingDto.getRatingId());
        rating.setRating(ratingDto.getRating());
        rating.setFeedback(ratingDto.getFeedback());

        return ratingRepository.save(rating);

    }

    @Override
    public List<Rating> getRatings() {
        return ratingRepository.findAll();
    }

    @Override
    public List<Rating> getRatingByUserId(String userId) {
        return ratingRepository.findByUserId(userId);
    }

    @Override
    public List<Rating> getRatingByHotelId(String hotelId) {
        return ratingRepository.findByHotelId(hotelId);
    }
}
