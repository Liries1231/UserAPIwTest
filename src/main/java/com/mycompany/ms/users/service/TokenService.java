package com.mycompany.ms.users.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    @Value("${jwt.secret}")
    private String secret;

    public boolean validateToken(String token) {
        try {
            JWTVerifier verifier = JWT.require(com.auth0.jwt.algorithms.Algorithm.HMAC256(secret))
                    .build();
            DecodedJWT decodedJWT = verifier.verify(token); 
            return decodedJWT != null;
        } catch (JWTVerificationException exception) {
            return false;
        }
    }
}
