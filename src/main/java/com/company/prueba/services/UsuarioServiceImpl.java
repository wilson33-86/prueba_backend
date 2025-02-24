package com.company.prueba.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.company.prueba.dao.IUsuarioRepository;
import com.company.prueba.model.Usuario;
import com.company.prueba.response.UsuarioResponseRest;



@Service
public class UsuarioServiceImpl implements IUsuarioServices {

	@Autowired
	private IUsuarioRepository usuarioDao;

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<UsuarioResponseRest> search() {
		 UsuarioResponseRest response = new  UsuarioResponseRest();
		 
		 try {		 
			 List<Usuario> usuario = (List<Usuario>) usuarioDao.findAll();
			 response.getUsuarioResponse().setUsuario(usuario);
			 response.setMetadata("Respuesta Ok", "200", "Respuesta success");
		 }catch(Exception e) {
			 response.setMetadata("Respuesta Error", "500", "Error en la respuesta");
			 e.getStackTrace();
			 return new ResponseEntity<UsuarioResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		 }
		 return new ResponseEntity<UsuarioResponseRest>(response, HttpStatus.OK);
	}
}
