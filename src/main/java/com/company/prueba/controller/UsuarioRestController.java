package com.company.prueba.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.company.prueba.response.UsuarioResponseRest;
import com.company.prueba.services.IUsuarioServices;


//@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/v1")
public class UsuarioRestController {
	@Autowired
	private IUsuarioServices service;
	
	@GetMapping("/usuarios")
	private ResponseEntity<UsuarioResponseRest> getUsuarios(){
		ResponseEntity<UsuarioResponseRest> response = service.search();
		return response;
	}
}
