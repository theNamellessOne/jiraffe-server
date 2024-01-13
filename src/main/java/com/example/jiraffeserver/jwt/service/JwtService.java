package com.example.jiraffeserver.jwt.service;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    @Value("${token.secret}")
    private String SECRET_KEY;

    @Value("#{1000 * 60 * 60 * 24 * ${token.expiration.days}}")
    private Long DURATION;

    private Key getSignedKey() {
        byte[] keyInBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyInBytes);
    }

    public String generateJwt(UserDetails user) {
        return this.generateJwt(new HashMap<>(), user);
    }

    public String generateJwt(Map<String, Object> additionalClaims, UserDetails user) {
        return Jwts.builder()
                .setClaims(additionalClaims)
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + DURATION))
                .signWith(getSignedKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUserName(String jwt) {
        return this.extractOneClaim(jwt, Claims::getSubject);
    }

    private Claims extractAllClaims(String jwt) {
        return Jwts.parserBuilder()
                .setSigningKey(this.getSignedKey())
                .build()
                .parseClaimsJws(jwt)
                .getBody();
    }

    private <T> T extractOneClaim(String jwt, Function<Claims, T> resolver) {
        final Claims claims = this.extractAllClaims(jwt);
        return resolver.apply(claims);
    }

    public boolean isJwtValid(String jwt, UserDetails userDetails) {
        String username = this.extractUserName(jwt);
        return (username.equals(userDetails.getUsername())   );
    }

    private boolean isJwtExpired(String jwt) {
        return this.getExpirationDate(jwt).before(new Date());
    }

    private Date getExpirationDate(String jwt) {
        return this.extractOneClaim(jwt, Claims::getExpiration);
    }
}
