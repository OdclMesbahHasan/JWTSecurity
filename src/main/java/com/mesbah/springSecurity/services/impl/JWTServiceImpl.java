package com.mesbah.springSecurity.services.impl;

import com.mesbah.springSecurity.services.JWTService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Service
public class JWTServiceImpl implements JWTService {

    public String generateToken(UserDetails userDetails) {
        return Jwts.builder().setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()* 1000*60*24))
                .signWith(SignatureAlgorithm.HS256, getSignKey())
                .compact();
    }

    private <T> T extractClaim(String token, Function<Claims, T>ClaimsResolvers) {
        final Claims claims = extractAllClaims(token);
        return ClaimsResolvers.apply(claims);
    }

    private Key getSignKey() {
        byte[] key = Decoders.BASE64.decode("413hsdbske2e12ekdb2be2uhd2uddd");
        return Keys.hmacShaKeyFor(key);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(getSignKey()).parseClaimsJws(token).getBody();
    }


    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }


    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }


}
