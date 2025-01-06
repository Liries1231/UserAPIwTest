package com.mycompany.ms.users.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class TokenController {

    @Value("${jwt.secret}")
    private String secret;

    @GetMapping("/validate-token")
    public ResponseEntity<Map<String, Object>> validateToken(@RequestParam("token") String token) {
        Map<String, Object> response = new HashMap<>();
        boolean isValid = isTokenValid(token);

        if (isValid) {
            String userId = getUserIdFromToken(token);
            response.put("message", "Token is valid");
            response.put("userId", userId);
            log.info("Extracted userId from token: {}", userId);
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(response);
        } else {
            response.put("message", "Invalid token");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).contentType(MediaType.APPLICATION_JSON).body(response);
        }
    }

    private boolean isTokenValid(String token) {
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret))
                    .build();
            DecodedJWT decodedJWT = verifier.verify(token);
            return decodedJWT != null;
        } catch (JWTVerificationException exception) {
            return false;
        }
    }

    private String getUserIdFromToken(String token) {
        try {
            DecodedJWT decodedJWT = JWT.decode(token);
            log.info("Decoded token claims: {}", decodedJWT.getClaims());


            Long userId = decodedJWT.getClaim("userId").asLong();
            if (userId == null) {
                log.error("userId is null in token");
            } else {
                log.info("userId from token: {}", userId);
            }

            return userId != null ? userId.toString() : null;
        } catch (Exception e) {
            log.error("Error decoding token", e);
            return null;
        }
    }


}
