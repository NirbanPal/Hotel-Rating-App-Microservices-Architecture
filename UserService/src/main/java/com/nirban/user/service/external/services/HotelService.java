package com.nirban.user.service.external.services;


import com.nirban.user.service.entities.Hotel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name="HOTEL-SERVICE")
public interface HotelService {

    @GetMapping("/hotels/{hId}")
    Hotel getHotel(@PathVariable("hId") String hotelId);

    @PostMapping("/hotels")
    ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel);

    @DeleteMapping("/hotels/{hId}")
    Hotel deleteHotel(@PathVariable("hId") String hotelId);
}
