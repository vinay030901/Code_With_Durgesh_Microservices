package com.lcwd.user.service.impl;

import com.lcwd.user.entity.Hotel;
import com.lcwd.user.entity.Rating;
import com.lcwd.user.entity.User;
import com.lcwd.user.externalservice.HotelService;
import com.lcwd.user.repository.UserRepository;
import com.lcwd.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RestTemplate restTemplate;
    @Autowired
    private HotelService hotelService;

    public UserServiceImpl(UserRepository userRepository, RestTemplate restTemplate) {
        this.userRepository = userRepository;
        this.restTemplate = restTemplate;
    }

    @Override
    public User saveUser(User user) {
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        // fetch rating of the above user from rating service
        Rating[] ratingsOfUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/" + userId,
                Rating[].class);
        if (ratingsOfUser == null) {
            user.setRatings(new ArrayList<>());
            return user;
        }
        List<Rating> ratings = Arrays.stream(ratingsOfUser).collect(Collectors.toList());
        List<Rating> ratingList = ratings.stream().map(rating -> {
            // api call to hotel service to get the hotel, set the hotel to rating, return
            // the rating
            // ResponseEntity<Hotel>
            // hotelResponseEntity=restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/"+rating.getHotelId(),
            // Hotel.class);
            // Hotel hotel=hotelReponseEntity.getBody();
            Hotel hotel = hotelService.getHotel(rating.getHotelId());
            rating.setHotel(hotel);
            return rating;
        }).collect(Collectors.toList());
        user.setRatings(ratingList);
        return user;
    }
}
