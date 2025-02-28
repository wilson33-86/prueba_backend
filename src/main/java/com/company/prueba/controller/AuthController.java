package com.company.prueba.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.company.prueba.model.AuthResponse;
import com.company.prueba.model.LoginRequest;
import com.company.prueba.response.LoginResponseRest;
import com.company.prueba.services.IUsuarioServices;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
 
    @Autowired
    private IUsuarioServices usuarioService;


    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {    
		return ResponseEntity.ok(usuarioService.authenticate(loginRequest));
    	
    }
	
}

