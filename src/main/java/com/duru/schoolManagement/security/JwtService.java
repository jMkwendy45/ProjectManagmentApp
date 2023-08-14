package com.duru.schoolManagement.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

import java.security.Key;
import java.util.Date;

@Slf4j
@Component
public class JwtService {
    @Value("${proWriters.jwtToken}")
    private String jwtToken;
    @Value("${proWriter.expirationTime}")
    private String expiryDate;


    @Value("${proWriter.jwtCookie}")
    private  String  jwtCookie;

    public String generateToken(String userName) {
        return Jwts.builder().setSubject(userName).setIssuedAt(new Date()).setExpiration(
                new Date((new Date().getTime() + expiryDate))).signWith(getSignInKey(), SignatureAlgorithm.HS256).compact();

    }

    public Key getSignInKey() {

        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtToken));
    }

    public Boolean validateJwtToken(String jwtToken) {
        try {
            Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parse(jwtToken);
            return true;
        } catch (MalformedJwtException exception) {
            log.error("invalid jwt token {} ", exception.getMessage());
        } catch (ExpiredJwtException exception) {
            log.error("expires jwt token {} ", exception.getMessage());
        } catch (UnsupportedJwtException exception) {
            log.error("unsupported  jwt token {} ", exception.getMessage());
        } catch (IllegalArgumentException exception) {
            log.error(" jwt is not supported  jwt token {} ", exception.getMessage());
        }
        return false;
    }
    public String getUserNameFromJwt(String token){
        return Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(token).getBody().getSubject();
    }
    public ResponseCookie getCleanJwtCookie(){
        ResponseCookie cookie = ResponseCookie.from(jwtCookie,null).path("/api").build();
        return cookie;
    }
    public ResponseCookie generateCookie(UserDetailImplemenation userDetailImplemenation){
        String generate = generateToken(userDetailImplemenation.getUsername());
        ResponseCookie cookie = ResponseCookie.from(jwtCookie,generate).path("/api")
                .maxAge(24*60*60).httpOnly(true).build();
        return cookie;
    }
    public String getJwtFromCookie(HttpServletRequest request){
        Cookie cookie  = WebUtils.getCookie(request,jwtCookie);
        if (cookie!=null){
            return cookie.getValue();
        }else {
            return null;
        }
    }
}
