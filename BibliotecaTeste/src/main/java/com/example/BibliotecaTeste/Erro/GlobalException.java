package com.example.BibliotecaTeste.Erro;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleValidation(MethodArgumentNotValidException ex) {
		Map<String, String> erros = new HashMap<>();
		ex.getBindingResult().getFieldErrors().forEach(error -> {
			erros.put(error.getField(), error.getDefaultMessage());

		});
		return ResponseEntity.badRequest().body(erros);
	}
}
