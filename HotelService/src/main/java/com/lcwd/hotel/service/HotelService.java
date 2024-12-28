package com.lcwd.hotel.service;

import java.util.List;

import com.lcwd.hotel.entity.Hotel;

public interface HotelService {

    Hotel createHotel(Hotel hotel);

    List<Hotel> getAllHotels();

    Hotel getHotelById(String id);
}
