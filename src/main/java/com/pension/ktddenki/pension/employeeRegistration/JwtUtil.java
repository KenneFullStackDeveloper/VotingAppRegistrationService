package com.pension.ktddenki.pension.employeeRegistration;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

        // Replace with a strong Key: Save it in Environment file during production
        private static final String SECRET_KEY = "YfMtWiT0UomVRlrLmsHFD3P2yCyqbBuP16EY7AJutpg=";
        // key
        private static final long EXPIRATION_TIME = 86400000; // 1 day (in milliseconds)

        private Key getSigningKey() {
            return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
        }

        //Generate JWT Token for loggedIn user
        public String generateToken(String email) {
            //Map<String, Object> claims = new HashMap<>();
            //claims.put("role", roles);
            return Jwts.builder()
               //   .setClaims(claims)

                    .setSubject(email).setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                    .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                    .compact();
        }

        //Extract Email from JWT Token
        public String extractEmail(String token) {
            return Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        }

    public String extractRole(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("role", String.class);
    }



        //Validate JWT Token
        public boolean validateToken(String token, String email) {
            return extractEmail(token).equals(email);
        }
    }


