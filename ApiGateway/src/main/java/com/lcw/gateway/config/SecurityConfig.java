package com.lcw.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain getSecurityWebFilterChain(ServerHttpSecurity httpSecurity) {
        httpSecurity.authorizeExchange(exchange -> exchange
                .anyExchange().authenticated())
                .oauth2Login(withDefaults())
                .oauth2ResourceServer(server -> server
                        .jwt(withDefaults()));
        return httpSecurity.build();
    }

}
