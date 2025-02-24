package com.company.prueba.services;

import org.springframework.http.ResponseEntity;

import com.company.prueba.response.UsuarioResponseRest;


public interface IUsuarioServices {
	ResponseEntity<UsuarioResponseRest> search();
}
