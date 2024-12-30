package com.lcw.gateway.dto;

import java.util.Collection;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthResponse {

    private String userId;
    private String accessToken;
    private String refreshToken;
    private long expiredAt;
    private Collection<String> authorities;
}
