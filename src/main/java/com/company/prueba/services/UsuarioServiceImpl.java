package com.company.prueba.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.company.prueba.dao.IUsuarioRepository;
import com.company.prueba.model.AuthResponse;
import com.company.prueba.model.LoginRequest;
import com.company.prueba.model.Usuario;
import com.company.prueba.response.LoginResponseRest;
import com.company.prueba.response.UsuarioResponseRest;
import com.company.prueba.security.JwtService;



@Service
public class UsuarioServiceImpl implements IUsuarioServices {

	
	private final IUsuarioRepository usuarioDao;	
	private final AuthenticationManager authenticationManager;
	private final JwtService jwtService;

	
	public UsuarioServiceImpl(IUsuarioRepository usuarioDao, AuthenticationManager authenticationManager,
			JwtService jwtService) {	
		this.usuarioDao = usuarioDao;
		this.authenticationManager = authenticationManager;
		this.jwtService = jwtService;
	}

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

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<LoginResponseRest> login(String email, String password) {
//		LoginResponseRest response = new  LoginResponseRest();
//        Usuario usuario = usuarioDao.findByUsuEmail(email);    
//      
//        if (usuario != null && usuario.getUsuPassword().equals(password)) {
//        	 response.getLoginResponse().setToken(JwtService.generateToken(usuario.getUsuEmail(), usuario.getUsuRol()));
//        	 response.setMetadata("Respuesta Ok", "200", "Respuesta success");
//
//        } else {
//        	response.setMetadata("Respuesta Error", "500", "Error en la respuesta");   		 
//   		    return new ResponseEntity<LoginResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
        return  null;
	}

	@Override
	public AuthResponse authenticate(LoginRequest request) {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						request.getEmail(), 
						request.getPassword()
						)
				);
		
		var user = usuarioDao.findByUsuEmail(request.getEmail());
		var jwtToken = jwtService.generateToken(user.getUsuEmail(), user.getUsuRol());
		return  new AuthResponse(jwtToken);
	}
}
