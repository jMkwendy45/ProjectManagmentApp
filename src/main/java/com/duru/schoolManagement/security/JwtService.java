package com.duru.schoolManagement.security;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;



public class JwtService {
    @Value("${proWriters.jwtToken}")
    private String jwtToken;
    @Value("${proWriter.expirationTime}")
    private String expiryDate;
    public JwtBuilder generateToken(UserDetails userDetails){
        return generateToke(new HashMap<>(),userDetails);
    }
    public JwtBuilder generateToke(Map<String,Object>extraClaims, UserDetails userDetails){
        return Jwts.builder().setClaims(extraClaims).setSubject(userDetails.getUsername()).
                setIssuedAt(new Date(System.currentTimeMillis())).setExpiration(
                        new Date(System.currentTimeMillis()+expiryDate)).signWith(getSignInKey(),
                        SignatureAlgorithm.HS256);

    }
    public Key getSignInKey(){
        byte[]keyBytes= Decoders.BASE64.decode(jwtToken);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
