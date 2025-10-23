package com.linfeng.spring1910.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {
    public static Map<String, Object> parseToken(String token) throws JWTVerificationException {
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("yuan123456")).build();
        return jwtVerifier.verify(token).getClaim("user").asMap();
    }
    public static String genToken(Map<String,?> claims){
        return JWT.create().withClaim("user",claims)
                .withExpiresAt(new Date(System.currentTimeMillis()+1000*60*15))
                .sign(Algorithm.HMAC256("yuan123456"));
    }
}
