package com.lcwd.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity security) throws Exception {
        security.authorizeHttpRequests(authorize -> authorize
                .anyRequest().authenticated())
                .oauth2Login(withDefaults())
                .oauth2ResourceServer(server -> server
                        .jwt(withDefaults()));
        return security.build();
    }
}
