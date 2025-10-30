package com.nirban.user.service;

import com.nirban.user.service.entities.Rating;
import com.nirban.user.service.external.services.RatingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceApplicationTests {

	@Test
	void contextLoads() {
	}

//	@Autowired
//	public RatingService ratingService;

//	@Test
//	void createRating(){
//		Rating rating  = Rating.builder().rating(3).hotelId("").feedback("Test feedback`").build();
//
//		Rating ratingRes = ratingService.create(rating).getBody();
//		System.out.println(ratingRes);
//	}


}
