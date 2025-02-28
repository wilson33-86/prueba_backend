package com.company.prueba.security;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.security.Key;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.spec.SecretKeySpec;


@Service
public class JwtService {

	private static final String SECRET_KEY = "4qhjdj8ENBjdkYEjkcmz2kdl9jhej8kiGTAHud";    
 
    private static final Long EXPIRATION_TIME = 3600000L;

    // Generar el token JWT
   
	public String generateToken(String email, String role) {
    	 
    	 Map<String, Object> extra = new HashMap<>();
    	 extra.put("role", role);
    	 
        return Jwts.builder()
        		   .subject(email)
        		   .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))  // Expiración en 1 hora
        		   .claims(extra)                 
                   .signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()))
                   .compact();
    }
	
	@SuppressWarnings("deprecation")
	public static UsernamePasswordAuthenticationToken getAuthentication(String token) {
		try {
			Claims claims = Jwts.parser()
				      .setSigningKey(SECRET_KEY.getBytes())
				      .build()
				      .parseClaimsJws(token)
				      .getBody();
		
				    String email = claims.getSubject();
				    
				    return new UsernamePasswordAuthenticationToken(email,null,Collections.emptyList());
		}catch(JwtException e){
			return null;
		}
				  
	}

    // Extraer el rol del token JWT
    @SuppressWarnings("deprecation")
	public String extractRole(String token) {
    	  return ((JwtParser) Jwts.parser()
                  .setSigningKey(SECRET_KEY.getBytes()))
                  .parseClaimsJws(token)
                  .getBody()
                  .get("role", String.class);
    }

    // Validar el token JWT
    public boolean validateToken(String token, String email) {
        return (email.equals(extractSubject(token)) && !isTokenExpired(token));
    }

    // Extraer el sujeto (email) del token JWT
    @SuppressWarnings("deprecation")
	public String extractSubject(String token) {
    	return ((JwtParser) Jwts.parser()
                .setSigningKey(SECRET_KEY.getBytes()))
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // Verificar si el token ha expirado
    @SuppressWarnings("deprecation")
	private boolean isTokenExpired(String token) {
    	return ((JwtParser) Jwts.parser()
                .setSigningKey(SECRET_KEY.getBytes()))
                .parseClaimsJws(token)
                .getBody()
                .getExpiration()
                .before(new Date());
    }

    
}
