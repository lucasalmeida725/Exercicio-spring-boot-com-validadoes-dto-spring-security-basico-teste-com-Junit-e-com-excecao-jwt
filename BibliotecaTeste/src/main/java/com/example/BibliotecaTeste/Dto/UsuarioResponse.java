package com.example.BibliotecaTeste.Dto;

public class UsuarioResponse {
	private String token;

	public UsuarioResponse(String token) {
		super();
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
