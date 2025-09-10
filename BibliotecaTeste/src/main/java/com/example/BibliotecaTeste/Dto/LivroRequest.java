package com.example.BibliotecaTeste.Dto;

import org.hibernate.validator.constraints.Range;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class LivroRequest {

	@NotBlank(message = "Nome do Livro é obrigatorio")
	private String titulo;
	@NotNull(message="Ano obrigatorio")
	@Range(min = 1960, max = 2026, message = "  ano deve esta dentro de 1960 e 2026")
	private Integer ano;

	@NotNull
	private Long idAutor;

	public LivroRequest() {
	}

	public LivroRequest(@NotBlank(message = "Nome do Livro é obrigatorio") String titulo,
			@NotBlank(message = "Ano obrigatorio") @Range(min = 1960, max = 2026, message = "  ano deve esta dentro de 1960 e 2026") Integer ano,
			@NotNull Long idAutor) {
		super();
		this.titulo = titulo;
		this.ano = ano;
		this.idAutor = idAutor;
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

	public Long getIdAutor() {
		return idAutor;
	}

	public void setIdAutor(Long idAutor) {
		this.idAutor = idAutor;
	}

}
