package com.company.prueba.security;

import java.io.IOException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.company.prueba.dao.IUsuarioRepository;
import com.company.prueba.model.LoginRequest;
import com.company.prueba.model.Usuario;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.JwtException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter{
	
	@Autowired
    private IUsuarioRepository usuarioRepository;

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws org.springframework.security.core.AuthenticationException {
		
	     
        LoginRequest loginRequest = null;
		try {
			loginRequest = new ObjectMapper().readValue(request.getInputStream(), LoginRequest.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		 
      
        Usuario usuario = usuarioRepository.findByUsuEmail(loginRequest.getEmail());

        if (usuario != null) {           
           
            if (usuario.getUsuPassword().equals(loginRequest.getPassword())) {
                
                UserDetails userDetails = User.builder()
                        .username(usuario.getUsuEmail())
                        .password(usuario.getUsuPassword())
                        .roles(usuario.getUsuRol()) 
                        .build();
               
                Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                // Establecemos el contexto de seguridad
                SecurityContextHolder.getContext().setAuthentication(authentication);

                // Generamos el JWT para este usuario
                String token;
				try {
//					token = JwtService.generateToken(usuario.getUsuEmail(), usuario.getUsuRol());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
//                response.addHeader("Authorization", "Bearer " + token);

                return authentication; // Retornamos la autenticación exitosa
            }
        }

        // Si las credenciales son incorrectas, lanzamos una excepción
        throw new JwtException("Credenciales inválidas");
	
	}
	

}
