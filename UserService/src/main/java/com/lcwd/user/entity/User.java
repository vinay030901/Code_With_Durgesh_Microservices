package com.lcwd.user.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "micro_users")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class User {

    @Id
    @Column(name = "ID")
    private String userId;
    @Column(name = "NAME", length = 20)
    private String name;
    @Column(name = "EMAIL", length = 50)
    private String email;
    @Column(name = "ABOUT")
    private String about;
    @Transient // this will not be shared in database
    @Builder.Default
    @Column(name = "RATINGS")
    private List<Rating> ratings = new ArrayList<>();
}
