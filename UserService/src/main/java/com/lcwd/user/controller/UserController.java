package com.lcwd.user.controller;

import com.lcwd.user.entity.User;
import com.lcwd.user.service.UserService;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/users")
public class UserController {

    final private UserService userService;
    Logger logger = LoggerFactory.getLogger(UserController.class);

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasAuthority('Admin')")
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUser(user));
    }

    int retryCount = 1;

    // @CircuitBreaker(name = "rating_hotel_from_user_circuitbreaker",
    // fallbackMethod = "ratingHotelFallback")
    @GetMapping("/{userId}")
    // @Retry(name = "rating_hotel_from_user_retry", fallbackMethod =
    // "ratingHotelFallback")
    @RateLimiter(name = "rating_hotel_from_user_ratelimiter", fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<User> getUserById(@PathVariable String userId) {
        logger.info("Retry count: {}", retryCount);
        retryCount++;
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    // creating fallback from circuit breaker
    public ResponseEntity<User> ratingHotelFallback(String userId, Exception ex) {
        // logger.info("Fallback method called because service is down for userId: " +
        // userId);
        ex.printStackTrace();
        User user = User.builder().email("dummy@gmail.com").name("dummy").userId("dummy")
                .about("this user is returned because service is down").build();
        return new ResponseEntity<>(user, HttpStatus.SERVICE_UNAVAILABLE);
    }

}
