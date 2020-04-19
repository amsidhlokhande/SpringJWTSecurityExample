package com.amsidh.mvc.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class JwtUtility {

    @Value("${myapp.jwtSecret}")
    private String jwtTokenSecret;

    @Value("${myapp.jwtExpirationMs}")
    private Long jwtExpirationMs;

    public String generateJwtToken(Authentication authentication) {
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        return Jwts.builder().setSubject(principal.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtTokenSecret)
                .compact();
    }

    public String getUserNameFromJwtToken(String jwtToken) {
        return Jwts.parser()
                .setSigningKey(jwtTokenSecret)
                .parseClaimsJws(jwtToken)
                .getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser()
                    .setSigningKey(jwtTokenSecret)
                    .parseClaimsJws(authToken);
            return true;
        } catch (Exception ex) {
            log.error("JWT Issue", ex.getMessage());
        }

        return false;
    }
}
