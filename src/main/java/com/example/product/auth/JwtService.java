package com.example.product.auth;

import com.example.product.entity.Token;
import com.example.product.repository.TokenRepo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    private final TokenRepo tokenRepo;

    public JwtService(TokenRepo tokenRepo) {
        this.tokenRepo = tokenRepo;
    }

    public String generateToken(String username){
        return generateToken(new HashMap<>(), username);
    }

    public String generateToken(Map<String,Object> extraClaims, String username){
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 3))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(com.example.product.config.Key.SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private Claims extractAllClaim(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public <T> T extractClaim(String token, Function<Claims,T> claimssResolver){
        final Claims claims = extractAllClaim(token);
        return claimssResolver.apply(claims);
    }

    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }

    private boolean isTokenExpired(String token) {
        if(extractExpiration(token).before(new Date())){
            Token old_jwt = tokenRepo.get(token);
            old_jwt.set_expired(true);
            tokenRepo.update(old_jwt);
            return true;
        }else{
            return false;
        }
    }

    private boolean isTokenProvoked(String token) {
        Token old_jwt = tokenRepo.get(token);
        if(old_jwt.is_provoked()){
            old_jwt.set_provoked(true);
            tokenRepo.update(old_jwt);
            return true;
        }else{
            return false;
        }
    }

    private Date extractExpiration(String token) {
        return extractClaim(token,Claims::getExpiration);
    }

    public boolean isTokenValid(String token, UserDetails userDetails){
        final String username = extractUsername(token);
        if(tokenRepo.isExist(token)){
            return (username.equals(userDetails.getUsername()) && !isTokenExpired(token) && !isTokenProvoked(token));
        }else{
            return false;
        }
    }

}
