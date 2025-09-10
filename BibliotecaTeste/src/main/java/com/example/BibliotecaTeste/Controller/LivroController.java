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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.BibliotecaTeste.Dto.LivroRequest;
import com.example.BibliotecaTeste.Dto.LivroResponse;
import com.example.BibliotecaTeste.Service.LivroService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/livro")
public class LivroController {

	private final LivroService livroService;

	public LivroController(LivroService livroService) {
		this.livroService = livroService;
	}

	@PostMapping
	public ResponseEntity<LivroResponse> Criar(@RequestBody @Valid LivroRequest dto) {
		LivroResponse response = livroService.criar(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@GetMapping("/{id}")
	public ResponseEntity<LivroResponse> ProcuarId(@PathVariable Long id) {
		LivroResponse response = livroService.procurarId(id);
		return ResponseEntity.ok(response);
	}

	@GetMapping("/buscar")
	public ResponseEntity<LivroResponse> ProcuarNome(@RequestParam String titulo) {
		LivroResponse response = livroService.procurarNome(titulo);
		return ResponseEntity.ok(response);
	}

	@GetMapping
	public ResponseEntity<List<LivroResponse>> listar() {
		return ResponseEntity.ok(livroService.listar());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> Deletar(@PathVariable Long id) {
		livroService.RemoverLivro(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<LivroResponse> Atualizar(@PathVariable Long id, @RequestBody @Valid LivroRequest dto) {
		LivroResponse response = livroService.Atualizar(id, dto);
		return ResponseEntity.ok(response);
	}

}
