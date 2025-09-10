package com.example.BibliotecaTeste.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.BibliotecaTeste.Dto.AutorRequest;
import com.example.BibliotecaTeste.Dto.AutorResponse;
import com.example.BibliotecaTeste.Service.AutorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/autor")
public class AutorController {

	private final AutorService service;

	public AutorController(AutorService service) {

		this.service = service;
	}

	@PostMapping
	public ResponseEntity<AutorResponse> criar(@RequestBody @Valid AutorRequest dto) {
		AutorResponse response = service.criar(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@GetMapping("/{id}")
	public ResponseEntity<AutorResponse> procuraId(@PathVariable Long id) {
		AutorResponse response = service.ProcurarId(id);
		return ResponseEntity.ok(response);
	}

	@GetMapping
	public ResponseEntity<List<AutorResponse>> listar() {
		return ResponseEntity.ok(service.listar());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> Deletar(@PathVariable Long id) {
		service.deletar(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<AutorResponse> Atualizar(@PathVariable Long id, @RequestBody @Valid AutorRequest dto) {
		AutorResponse response = service.atualizar(id, dto);
		return ResponseEntity.ok(response);
	}

}
