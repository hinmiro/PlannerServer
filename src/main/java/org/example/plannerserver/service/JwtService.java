package org.example.plannerserver.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Base64;

import static org.springframework.cache.interceptor.SimpleKeyGenerator.generateKey;


@Service
public class JwtService {
    private static final String SECRET_KEY = System.getenv("SECRET_KEY");

//    public String extractUsername(String token) {
//        return extractAllClaims(token).getSubject();
//    }

//    private Claims extractAllClaims(String token) {
//        return Jwts.builder().subject("joe").signWith(generateKey()).compact();
//    }

    private Key generateKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
