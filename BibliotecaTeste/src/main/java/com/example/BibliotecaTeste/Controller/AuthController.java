package com.example.BibliotecaTeste.Controller;

import java.util.Collections;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.BibliotecaTeste.Dto.UsuarioRequest;
import com.example.BibliotecaTeste.Dto.UsuarioResponse;
import com.example.BibliotecaTeste.Repository.UsuarioRepository;
import com.example.BibliotecaTeste.config.JwtUtil;
import com.example.BibliotecaTeste.model.Usuario;

@RestController
@RequestMapping("/auth")
public class AuthController {

	private final UsuarioRepository repository;
	private final PasswordEncoder encoder;

	public AuthController(UsuarioRepository repository, PasswordEncoder encoder) {
		this.repository = repository;
		this.encoder = encoder;
	}

	@PostMapping("/registro")
	public ResponseEntity<?> registrar(@RequestBody UsuarioRequest dto) {
		if (repository.findByEmail(dto.getEmail()).isPresent()) {
			return ResponseEntity.badRequest().body("Email já cadastrado");
		}

		Usuario usuario = new Usuario();
		usuario.setEmail(dto.getEmail());
		usuario.setSenha(encoder.encode(dto.getSenha()));
		usuario.setRoles(Collections.singleton("ROLE_USER"));
		String token=JwtUtil.generateToken(usuario.getEmail(),usuario.getRoles());
		repository.save(usuario);
		return ResponseEntity.ok("Usuário criado");
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody UsuarioRequest dto) {
		Usuario usuario = repository.findByEmail(dto.getEmail())
				.orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

		if (!encoder.matches(dto.getSenha(), usuario.getSenha())) {
			return ResponseEntity.status(401).body("Senha inválida");
		}

		String token = JwtUtil.generateToken(usuario.getEmail(), usuario.getRoles());
		return ResponseEntity.ok(new UsuarioResponse(token));
	}
}
