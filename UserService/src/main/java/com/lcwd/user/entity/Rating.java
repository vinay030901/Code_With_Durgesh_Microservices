package com.lcwd.user.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Rating {

    private String ratingId;
    private String userId;
    private String hotelId;
    private int ratingNumber;
    private String feedback;
    private Hotel hotel;
}
