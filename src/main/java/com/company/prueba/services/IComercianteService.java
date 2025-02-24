package com.company.prueba.services;

import org.springframework.http.ResponseEntity;

import com.company.prueba.response.ComercianteMunicipiosResponseRest;

public interface IComercianteService {
	 ResponseEntity<ComercianteMunicipiosResponseRest> getMunicipios();
}
