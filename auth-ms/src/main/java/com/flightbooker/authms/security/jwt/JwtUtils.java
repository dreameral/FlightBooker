package com.flightbooker.authms.security.jwt;


import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {
    @Value("1000000")
    private int jwtExpirationMs;
    @Value("{jwt.secret}")
    private String jwtSecret;

    public String generateJwtToken(String username, String password) {
        return Jwts.builder().setSubject(username + "|" + password).setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
    }

}
