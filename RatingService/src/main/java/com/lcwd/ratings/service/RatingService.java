package com.lcwd.ratings.service;

import com.lcwd.ratings.entity.Rating;

import java.util.List;


public interface RatingService {

    Rating createRating(Rating rating);

    List<Rating> getAllRatings();

    List<Rating> getAllRatingsByUserId(String userId);

    List<Rating> getAllRatingsByHotelId(String hotelId);
}
