package com.company.prueba.security;
import org.springframework.stereotype.Service;


import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.beans.factory.annotation.Value;

import java.security.Key;
import java.util.Date;
import javax.crypto.spec.SecretKeySpec;


@Service
public class JwtService {

	 private static final String SECRET_KEY = "pruebaSpring999999999999999999tttttttttttttttttttttttteeeee0000000000000000wlllllllllllllllllwkkkkkkkkkkkkkk";

    @Value("${spring.security.jwt.expiration}")
    private long expirationTime;

    // Generar el token JWT
    @SuppressWarnings("deprecation")
	public String generateToken(String email, String role) {
    	 Key key = new SecretKeySpec(SECRET_KEY.getBytes(), SignatureAlgorithm.HS256.getJcaName());
        return Jwts.builder()
        		   .setSubject(email)
                   .claim("role", role)
                   .setIssuedAt(new Date())
                   .setExpiration(new Date(System.currentTimeMillis() + 3600000))  // Expiración en 1 hora
                   .signWith(key, SignatureAlgorithm.HS256)
                   .compact();
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
