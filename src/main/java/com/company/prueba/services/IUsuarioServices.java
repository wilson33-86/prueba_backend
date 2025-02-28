package com.company.prueba.services;

import org.springframework.http.ResponseEntity;

import com.company.prueba.model.AuthResponse;
import com.company.prueba.model.LoginRequest;
import com.company.prueba.response.LoginResponseRest;
import com.company.prueba.response.UsuarioResponseRest;


public interface IUsuarioServices {
	ResponseEntity<UsuarioResponseRest> search();
	ResponseEntity<LoginResponseRest> login(String email, String password);
	AuthResponse authenticate (LoginRequest request);
}
