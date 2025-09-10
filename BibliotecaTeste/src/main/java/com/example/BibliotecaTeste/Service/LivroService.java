package com.example.BibliotecaTeste.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.BibliotecaTeste.Dto.LivroRequest;
import com.example.BibliotecaTeste.Dto.LivroResponse;
import com.example.BibliotecaTeste.Repository.AutoRepository;
import com.example.BibliotecaTeste.Repository.LivroRepository;
import com.example.BibliotecaTeste.model.Autor;
import com.example.BibliotecaTeste.model.Livro;

@Service
public class LivroService {
	private final AutoRepository autoRepository;
	private final LivroRepository livroRepository;

	public LivroService(AutoRepository autoRepository, LivroRepository livroRepository) {
		this.autoRepository = autoRepository;
		this.livroRepository = livroRepository;
	}

	public LivroResponse criar(LivroRequest dto) {
		Autor autor = autoRepository.findById(dto.getIdAutor())
				.orElseThrow(() -> new RuntimeException("Autor nao existe"));

		Livro livro = new Livro();
		livro.setTitulo(dto.getTitulo());
		livro.setAno(dto.getAno());
		livro.setAutor(autor);

		Livro salvar = livroRepository.save(livro);
		return new LivroResponse(salvar);
	}

	public LivroResponse procurarNome(String titulo) {
		Livro livro = livroRepository.findByTituloContainingIgnoreCase(titulo)
				.orElseThrow(() -> new RuntimeException("Nao existe com esse nome "));
		return new LivroResponse(livro);
	}

	public LivroResponse procurarId(Long id) {
		Livro livro = livroRepository.findById(id).orElseThrow(() -> new RuntimeException("Id nao existe"));
		return new LivroResponse(livro);
	}

	public void RemoverLivro(Long id) {
		if (!livroRepository.existsById(id)) {
			throw new RuntimeException("Livro nao existe");
		}
		livroRepository.deleteById(id);
	}

	public List<LivroResponse> listar() {
		return livroRepository.findAll().stream().map(LivroResponse::new).collect(Collectors.toList());
	}

	public LivroResponse Atualizar(Long id, LivroRequest dto) {
		Autor autor = autoRepository.findById(dto.getIdAutor())
				.orElseThrow(() -> new RuntimeException("Autor nao existe"));

		Livro livro = livroRepository.findById(id).orElseThrow(() -> new RuntimeException("Livro nao existe"));

		livro.setAno(dto.getAno());
		livro.setAutor(autor);
		livro.setTitulo(dto.getTitulo());

		Livro atualizar = livroRepository.save(livro);
		return new LivroResponse(atualizar);

	}
}
