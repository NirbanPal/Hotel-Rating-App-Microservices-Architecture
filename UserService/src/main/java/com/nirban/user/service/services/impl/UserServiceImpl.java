package com.nirban.user.service.services.impl;

import com.nirban.user.service.dto.requestDto.UserDto;
import com.nirban.user.service.entities.Hotel;
import com.nirban.user.service.entities.Rating;
import com.nirban.user.service.entities.User;
import com.nirban.user.service.exceptions.ResourceNotFoundException;
import com.nirban.user.service.external.services.HotelService;
import com.nirban.user.service.repositories.UserRepository;
import com.nirban.user.service.services.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HotelService hotelService;


    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User saveUser(UserDto userDto) {
        //generate  unique userid
        String randomUserId = UUID.randomUUID().toString();
        userDto.setUserId(randomUserId);
        User userDb = new User();
        userDb.setUserId(randomUserId);
        userDb.setName(userDto.getName());
        userDb.setEmail(userDto.getEmail());
        userDb.setAbout(userDto.getAbout());
        
        return userRepository.save(userDb);
    }

    @Override
    public List<User> getAllUser() {
        //implement RATING SERVICE CALL: USING REST TEMPLATE
        return userRepository.findAll();
    }

    @Override
    public User getUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with given id is not found on server !! : " + userId));
        //fetching rating of the above user from RATING SERVICE
        Rating[] ratingsByUserArray = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/"+user.getUserId(), Rating[].class);
        List<Rating> ratingsByUser = Arrays.stream(ratingsByUserArray).toList();
        user.setRatings(ratingsByUser);
        logger.info(ratingsByUser.toString());

        List<Rating> forEntity = ratingsByUser.stream().map(rating->{
            //fetch details of hotel from HOTEL SERVICE
//            ResponseEntity<Hotel> hotelDetails = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/"+rating.getHotelId(), Hotel.class);
//            logger.info("response status code: {}",hotelDetails.getStatusCode());
//            Hotel hotelDetailsInfo = hotelDetails.getBody();
            Hotel hotelDetailsInfo = hotelService.getHotel(rating.getHotelId());
            //set hotel details to rating
            rating.setHotel(hotelDetailsInfo);
            //return rating
            return rating;

        }).toList();


        return user;
    }

    @Override
    public void deleteUser(String userId) {

    }

    @Override
    public User updateUser(String userId, User user) {
        return null;
    }
}
