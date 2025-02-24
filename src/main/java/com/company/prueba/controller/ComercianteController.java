package com.company.prueba.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.prueba.response.ComercianteMunicipiosResponseRest;
import com.company.prueba.services.IComercianteService;



@RestController
@RequestMapping("/api/comerciantes")
public class ComercianteController {

	 @Autowired
	    private IComercianteService comercianteService;

	 @GetMapping("/municipios")
	    @PreAuthorize("hasRole('Administrador') or hasRole('Auxiliar')")
	    public ResponseEntity<ComercianteMunicipiosResponseRest> getMunicipios() {
		    ResponseEntity<ComercianteMunicipiosResponseRest> municipios = comercianteService.getMunicipios();
	        return municipios;
	    }

}

