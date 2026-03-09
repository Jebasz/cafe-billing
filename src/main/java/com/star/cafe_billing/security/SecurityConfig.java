package com.star.cafe_billing.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http)
            throws Exception {

        http

                .cors(cors -> {}) // Enable CORS

                .csrf(AbstractHttpConfigurer::disable)

                .sessionManagement(session ->
                        session.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS))

                .authorizeHttpRequests(auth -> auth

                        .requestMatchers("/auth/**")
                        .permitAll()
                        .requestMatchers("/uploads/**").permitAll()
                        .requestMatchers("/products/**")
                        .hasAnyRole("ADMIN","STAFF")

                        .requestMatchers("/reports/**")
                        .hasRole("ADMIN")

                        .requestMatchers("/users/**")
                        .hasAnyRole("ADMIN","STAFF")

                        .requestMatchers("/shops/**")
                        .hasAnyRole("ADMIN","STAFF")

                        .requestMatchers("/categories/**")
                        .hasAnyRole("ADMIN","STAFF")

                        .requestMatchers("/price-filters/**")
                        .hasAnyRole("ADMIN","STAFF")

                        .requestMatchers("/bills/**")
                        .hasAnyRole("ADMIN","STAFF")

                        .anyRequest()
                        .authenticated()

                )

                .addFilterBefore(
                        jwtFilter,
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }
}