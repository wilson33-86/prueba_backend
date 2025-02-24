package com.company.prueba.security;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JwtService jwtService;  // Asegúrate de inyectar tu servicio JWT

    public SecurityConfig(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @SuppressWarnings("removal")
	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Configuración de la seguridad web
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers("/api/auth/login").permitAll()  // Permitir acceso a login
                        .requestMatchers("/api/admin/**").hasRole("Administrador")  // Acceso solo para ADMIN
                        .requestMatchers("/api/auxiliar/**").hasRole("Auxiliar")  // Acceso solo para AUXILIAR
                        .requestMatchers("/api/comerciantes/municipios").authenticated()
                        .anyRequest().authenticated()  // Cualquier otra solicitud requiere autenticación
                        .and()
                        .addFilterBefore(new JwtAuthorizationFilter(jwtService), UsernamePasswordAuthenticationFilter.class));  // Añadir el filtro JWT

        return http.build();
    }
    
 
}
