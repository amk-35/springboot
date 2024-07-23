package com.example.demo.Security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;

import java.util.Arrays;
import java.util.Base64;
import java.util.Date;

public class JwtUtil {
    private static final String YOUR_SECRET_KEY = "testingtestingtestingtestingtestingtestingtesting"; // Replace with your actual secret
    private static final long EXP_10_DAYS=864_000_000;

    public static String generateToken(String username) {
        return Jwts.builder().setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXP_10_DAYS))
                .signWith(SignatureAlgorithm.HS256,YOUR_SECRET_KEY).compact();
    }

    public static String extractUsername(String token) {
      return   Jwts.parser().setSigningKey(YOUR_SECRET_KEY)
                .build()
              .parseSignedClaims(token).getPayload().getSubject();
    }

    public static boolean validateToken(String token) {
        try{
            Jwts.parser().setSigningKey(YOUR_SECRET_KEY)
                    .build().parseSignedClaims(token);
            return true;
        }catch (SignatureException | ExpiredJwtException e){
            System.out.println(e.getMessage());
        }
        return false;
    }

}
