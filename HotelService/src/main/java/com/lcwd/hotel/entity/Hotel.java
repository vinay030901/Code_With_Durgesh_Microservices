package com.lcwd.hotel.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "hotels")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Hotel {

    @Id
    private String hotelId;
    private String name;
    private String location;
    private String about;
}
