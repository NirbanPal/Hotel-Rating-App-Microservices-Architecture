package com.nirban.user.service.external.services;


import com.nirban.user.service.entities.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@service
@FeignClient("RATING-SERVICE")
public interface RatingService {


    @GetMapping("/ratings/users/{userId}")
    ResponseEntity<Rating> getRatingsByUserId(@PathVariable String userId);

    @PostMapping("/ratings")
    ResponseEntity<Rating> create(@RequestBody Rating rating);

    @PutMapping("/ratings/{ratingId}")
    ResponseEntity<Rating> updateRating(@PathVariable String ratingId, @RequestBody Rating rating);


    @DeleteMapping("/ratings/{ratingId}")
    void deleteMapping(@PathVariable String ratingId);
}
