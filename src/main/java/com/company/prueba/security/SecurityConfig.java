package com.company.prueba.security;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {
	
	private final JwtAuthorizationFilter jwtAuthorizationFilter;
    private  final AuthenticationProvider authenticationProvider;
    
	public SecurityConfig(JwtAuthorizationFilter jwtAuthorizationFilter,
			AuthenticationProvider authenticationProvider) {
		super();
		this.jwtAuthorizationFilter = jwtAuthorizationFilter;
		this.authenticationProvider = authenticationProvider;
	}

	

	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers("/api/auth/login").permitAll() 
                        .requestMatchers("/api/admin/**").hasRole("ADMIN")  
                        .requestMatchers("/api/auxiliar/**").hasRole("AUXILIAR")  
                        .requestMatchers("/api/comerciantes/municipios").hasRole("ADMIN")    
                        .anyRequest().authenticated())
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);
                        

        return http.build();
    }
    
 
}
