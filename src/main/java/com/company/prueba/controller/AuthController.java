package com.company.prueba.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.company.prueba.dao.IUsuarioRepository;
import com.company.prueba.model.LoginRequest;
import com.company.prueba.model.Usuario;
import com.company.prueba.response.LoginResponseRest;
import com.company.prueba.response.UsuarioResponseRest;
import com.company.prueba.security.JwtService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseRest> login(@RequestBody LoginRequest loginRequest) {
    	LoginResponseRest response = new  LoginResponseRest();
        Usuario usuario = usuarioRepository.findByUsuEmail(loginRequest.getEmail());        
        if (usuario != null && usuario.getUsuPassword().equals(loginRequest.getPassword())) {
        	 response.getLoginResponse().setToken(jwtService.generateToken(usuario.getUsuEmail(), usuario.getUsuRol()));
        	 response.setMetadata("Respuesta Ok", "200", "Respuesta success");

        } else {
        	response.setMetadata("Respuesta Error", "500", "Error en la respuesta");   		 
   		    return new ResponseEntity<LoginResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<LoginResponseRest>(response, HttpStatus.OK);
  
    }
	
}

