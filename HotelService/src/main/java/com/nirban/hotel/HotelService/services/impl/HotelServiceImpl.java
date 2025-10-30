package com.nirban.hotel.HotelService.services.impl;

import com.nirban.hotel.HotelService.dto.requestDto.HotelRequestDto;
import com.nirban.hotel.HotelService.entities.Hotel;
import com.nirban.hotel.HotelService.repositories.HotelRepository;
import com.nirban.hotel.HotelService.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;
import java.util.List;
import java.util.UUID;


@Service
public class HotelServiceImpl implements HotelService {


    @Autowired
    private HotelRepository hotelRepository;



    @Override
    public Hotel create(HotelRequestDto hotelDetails) {
        String hotelId = UUID.randomUUID().toString();
        Hotel hotel = new Hotel(hotelId, hotelDetails.getName(), hotelDetails.getLocation(),hotelDetails.getAbout());
        return hotelRepository.save(hotel);
    }

    @Override
    public List<Hotel> getAll() {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel get(String id) {

        return hotelRepository.findById(id).orElseThrow(()-> new ResolutionException(" Hotel does not found "));
    }
}
