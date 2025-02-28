package com.company.prueba.security;

import java.io.IOException;
import java.util.Arrays;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

     public JwtAuthorizationFilter(JwtService jwtService, UserDetailsService userDetailsService) {
		this.jwtService = jwtService;
		this.userDetailsService = userDetailsService;
	}


	@Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        
    	String token = request.getHeader("Authorization");

        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);  // Eliminar "Bearer " del token

            try {
                String email = jwtService.extractSubject(token);                               
                            
                if (email != null &&  SecurityContextHolder.getContext().getAuthentication() == null) {
                  
                	UserDetails userDetail = this.userDetailsService.loadUserByUsername(email);
                	if(jwtService.validateToken(token, email)) {
                		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetail, null,userDetail.getAuthorities());	
                		authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                		SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                	}
                	
                   
                }
            } catch (Exception e) {
                // Si el token no es válido, devolver un estado de no autorizado
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            }
        }

        filterChain.doFilter(request, response);  // Continuar con la cadena de filtros
    }
}
