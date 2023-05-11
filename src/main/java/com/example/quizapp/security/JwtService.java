package com.example.quizapp.security;

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


    @Value("${jwt.access.key}")
    private String accessTokenKey;

    @Value("${jwt.access.expirationTime}")
    private Long accessTokenExpirationTime;

//    @Value("${jwt.refresh.key}")
//    private String refreshTokenKey;
//
//    @Value("${jwt.refresh.expirationTime}")
//    private Long refreshTokenExpirationTime;






    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }


    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractALLClaims(token);
        return claimsResolver.apply(claims);
    }


    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(), userDetails);
    }

    public String generateToken(
            Map<String, Object> extraClaim,
            UserDetails userDetails
    ){
        return Jwts
                .builder()
                .setClaims(extraClaim)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + accessTokenExpirationTime))
                .signWith(getSigninKey(accessTokenKey), SignatureAlgorithm.HS256)
                .compact();
    }



    public boolean isTokenValid(String token, UserDetails userDetails){
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }


    private Claims extractALLClaims(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(getSigninKey(accessTokenKey))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSigninKey(String key) {
        byte [] keyBytes = Decoders.BASE64.decode(key);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
