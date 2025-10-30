package com.nirban.hotel.HotelService.services;

import com.nirban.hotel.HotelService.dto.requestDto.HotelRequestDto;
import com.nirban.hotel.HotelService.entities.Hotel;

import java.util.List;

public interface HotelService {

    //create

    Hotel create(HotelRequestDto hotel);

    //get all
    List<Hotel> getAll();

    //get single
    Hotel get(String id);

}
