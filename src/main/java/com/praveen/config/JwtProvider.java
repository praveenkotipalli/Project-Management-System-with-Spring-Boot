package com.praveen.config;

// import java.util.Collection;
import java.util.Date;

import javax.crypto.SecretKey;

// import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties.Authentication;
// import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JwtProvider {
      static SecretKey key = Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());

    //         Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();

    //         String email = String.valueOf(claims.get("email"));
    //         String authorities = String.valueOf(claims.get("authorities"));

    public static String generateToken(UsernamePasswordAuthenticationToken authentication){
        // Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
        String jwt = Jwts.builder().setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime()+86400000))
                .claim("email", authentication.getName())
                .signWith(key)
                .compact();

        return jwt;
    }

    public static String getEmailFromToken (String jwt){
         Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();

            String email = String.valueOf(claims.get("email"));
    

            return email;
    }
}
