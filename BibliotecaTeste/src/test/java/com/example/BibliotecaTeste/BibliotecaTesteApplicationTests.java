package com.example.BibliotecaTeste;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.BibliotecaTeste.Dto.AutorRequest;
import com.example.BibliotecaTeste.Repository.AutoRepository;
import com.example.BibliotecaTeste.Service.AutorService;
import com.example.BibliotecaTeste.model.Autor;

@SpringBootTest
class BibliotecaTesteApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private AutorService autorService;
	
	@Autowired
	private AutoRepository autorRepository;
	
	@Test
	void EmailDuplicado() {
		AutorRequest a1= new AutorRequest();
		a1.setEmail("lucas@gmail.com");
		a1.setNome("Lucas Almeida");
		autorService.criar(a1);
	}
}
