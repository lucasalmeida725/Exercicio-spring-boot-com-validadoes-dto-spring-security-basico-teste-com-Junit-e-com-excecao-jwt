package com.example.BibliotecaTeste.Dto;

import com.example.BibliotecaTeste.model.Autor;

public class AutorResponse {

	private Long id;
	private String nome;
	private String email;

	public AutorResponse(Autor autor) {
		this.id=autor.getId();
		this.email=autor.getEmail();
		this.nome=autor.getNome();
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
