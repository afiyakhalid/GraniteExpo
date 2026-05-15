package com.graniteexpo.demo.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;

public class JwtUtil {

    // This is the secret signing key used to sign/verify JWTs.
    // Anyone who has this secret can forge tokens, so keep it private (env var / secrets manager).
    private final Key key;

    // Token expiry time in seconds (e.g., 7 days).
    private final long ttlSeconds;

    public JwtUtil(String secret, long ttlSeconds) {
        // Convert the string secret into an HMAC key.
        // For HS256, secret should be long enough (32+ chars is a good rule).
        this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.ttlSeconds = ttlSeconds;
    }

    public String createToken(UUID userId, String email) {
        // JWT standard fields:
        // - subject (sub): we store userId
        // - issuedAt (iat): when token was created
        // - expiration (exp): when token expires
        // Custom claims:
        // - email, role
        Instant now = Instant.now();

        return Jwts.builder()
                .setSubject(userId.toString())
                .claim("email", email)

                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plusSeconds(ttlSeconds)))
                .signWith(key, SignatureAlgorithm.HS256) // Signs token so it cannot be tampered with
                .compact();
    }

    public Jws<Claims> parse(String token) {
        // Validates signature + expiration.
        // If invalid/expired, this throws an exception.
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token);
    }
}