package com.example.BibliotecaTeste.model;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Livro {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String titulo;
	private Integer ano;

	@ManyToOne
	@JoinColumn(name = "idAutor", nullable = false)
	private Autor autor;

	public Livro() {
	}

	public Livro(Long id, String titulo, Integer ano, Autor autor) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.ano = ano;
		this.autor = autor;
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

	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}

}
