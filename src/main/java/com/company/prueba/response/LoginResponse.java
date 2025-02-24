package com.company.prueba.response;

import java.util.HashMap;

public class LoginResponse{
	private HashMap<String, String> token = new HashMap<>();

	public HashMap<String, String> getToken() {
		return token;
	}
	
	public void setToken(String token) {
		this.token.put("valor", token);	
		
	}
	
	
}
