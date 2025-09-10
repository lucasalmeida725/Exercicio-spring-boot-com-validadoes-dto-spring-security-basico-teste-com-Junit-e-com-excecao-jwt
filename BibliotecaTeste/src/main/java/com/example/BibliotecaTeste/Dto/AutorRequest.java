package com.example.BibliotecaTeste.Dto;

import org.antlr.v4.runtime.misc.NotNull;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class AutorRequest {

	@NotBlank(message = "Nome Obrigatorio")
	@Size(min = 3, max = 30)
	private String nome;
	@Email(message = "Email invalido")
	@NotBlank(message = "Email obrigatorio")
	@Column(unique = true)
	private String email;

	public AutorRequest() {
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
