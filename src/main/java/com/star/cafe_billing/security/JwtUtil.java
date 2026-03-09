package com.star.cafe_billing.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {

    private final String SECRET =
            "CoffeeBillingJwtSecretKeyProductionSecure2026Key";

    private final SecretKey KEY =
            Keys.hmacShaKeyFor(SECRET.getBytes());

    public String generateToken(String username) {

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(
                        new Date(System.currentTimeMillis()
                                + 86400000)
                )
                .signWith(KEY, SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUsername(String token) {

        Claims claims =
                Jwts.parserBuilder()
                        .setSigningKey(KEY)
                        .build()
                        .parseClaimsJws(token)
                        .getBody();

        return claims.getSubject();
    }
}