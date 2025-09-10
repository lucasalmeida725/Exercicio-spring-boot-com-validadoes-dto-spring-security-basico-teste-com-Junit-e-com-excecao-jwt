package com.example.BibliotecaTeste.Dto;

import com.example.BibliotecaTeste.model.Livro;

public class LivroResponse {
	private Long id;
	private String titulo;
	private Integer ano;
	private Long idAutor;
	
	public LivroResponse(Livro livro) {
		this.id = livro.getId();
		this.ano = livro.getAno();
		this.titulo = livro.getTitulo();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}
	
}
