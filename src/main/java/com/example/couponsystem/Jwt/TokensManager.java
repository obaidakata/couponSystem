package com.example.couponsystem.Jwt;

import com.example.couponsystem.customExceptions.TokenExpiredException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeoutException;

@Component
@Scope("singleton")
public class TokensManager {

    private final String  key = "securesecuresecuresecuresecuresecuresecuresecuresecuresecuresecuresecuresecuresecuresecuresecuresecure";
    static final long ONE_MINUTE_IN_MILLIS=60000;//millisecs

    public UserNameAndPasswordAuthenticationRequest verifyToken(String token)  throws TokenExpiredException {
        try {
            Jws<Claims> claimsJws = Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(key.getBytes()))
                    .build()
                    .parseClaimsJws(token);

            JwsHeader header = claimsJws.getHeader();
            String email = (String) header.get("email");
            String password = (String) header.get("password");
            Date tokenExpiration = claimsJws.getBody().getExpiration();
            UserNameAndPasswordAuthenticationRequest userDetails = null;
            Date now = new Date();
            if (email != null && password != null && tokenExpiration.after(now)) {
                userDetails = new UserNameAndPasswordAuthenticationRequest(email, password);
            }
            return userDetails;
        } catch (Exception e) {
                throw new TokenExpiredException("Timeout");
        }
    }

    public String generateToken(UserNameAndPasswordAuthenticationRequest request) {
        Calendar date = Calendar.getInstance();
        long t = date.getTimeInMillis();
        Date afterAdding30Mins = new Date(t + (30 * ONE_MINUTE_IN_MILLIS));

        String token = Jwts
                .builder()
                .setHeaderParam("email", request.getEmail())
                .setHeaderParam("password", request.getPassword())
                .setIssuedAt(new Date())
                .setExpiration(afterAdding30Mins)
                .signWith(Keys.hmacShaKeyFor(key.getBytes()))
                .compact();
        return token;
    }

    public HttpHeaders getTokenHeader(String token)
    {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", token);
        return headers;
    }
}
