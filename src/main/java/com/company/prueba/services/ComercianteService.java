package com.company.prueba.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.company.prueba.dao.IComercianteRepository;
import com.company.prueba.response.ComercianteMunicipiosResponseRest;

@Service
public class ComercianteService implements IComercianteService {

    @Autowired
    private IComercianteRepository comercianteRepository;
    
    @Cacheable("municipios")
    public ResponseEntity<ComercianteMunicipiosResponseRest> getMunicipios() {            
        ComercianteMunicipiosResponseRest response = new  ComercianteMunicipiosResponseRest();		 
		 try {	 			 
			 response.getComercianteMunicipiosResponse().setMunicipios(comercianteRepository.findDistinctMunicipio());
			 response.setMetadata("Respuesta Ok", "200", "Respuesta success");
		 }catch(Exception e) {
			 response.setMetadata("Respuesta Error", "500", "Error en la respuesta");
			 e.getStackTrace();
			 return new ResponseEntity<ComercianteMunicipiosResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		 }
		 return new ResponseEntity<ComercianteMunicipiosResponseRest>(response, HttpStatus.OK);
    }
}
