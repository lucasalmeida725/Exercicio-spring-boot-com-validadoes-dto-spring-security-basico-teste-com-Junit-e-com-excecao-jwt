package com.example.BibliotecaTeste.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.BibliotecaTeste.model.Livro;

public interface LivroRepository extends JpaRepository<Livro,Long> {

	Optional<Livro>findByTituloContainingIgnoreCase(String titulo);
}
