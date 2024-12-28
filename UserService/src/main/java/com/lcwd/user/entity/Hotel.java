package com.lcwd.user.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Hotel {
    private String hotelId;
    private String name;
    private String location;
    private String about;
}
