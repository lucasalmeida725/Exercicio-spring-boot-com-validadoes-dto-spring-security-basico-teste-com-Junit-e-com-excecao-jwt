package com.example.BibliotecaTeste.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.BibliotecaTeste.Dto.AutorRequest;
import com.example.BibliotecaTeste.Dto.AutorResponse;
import com.example.BibliotecaTeste.Repository.AutoRepository;
import com.example.BibliotecaTeste.model.Autor;

@Service
public class AutorService {

	private final AutoRepository autoRepository;

	public AutorService(AutoRepository autoRepository) {
		this.autoRepository = autoRepository;
	}

	public AutorResponse criar(AutorRequest dto) {
		Autor autor = new Autor();
		autor.setEmail(dto.getEmail());
		autor.setNome(dto.getNome());
		Autor salvar = autoRepository.save(autor);
		return new AutorResponse(salvar);
	}

	public List<AutorResponse> listar() {
		return autoRepository.findAll().stream().map(AutorResponse::new).collect(Collectors.toList());
	}

	public AutorResponse ProcurarId(Long id) {
		Autor autor = autoRepository.findById(id).orElseThrow(() -> new RuntimeException("Autor nao existe"));
		return new AutorResponse(autor);
	}

	public void deletar(Long id) {
		if (!autoRepository.existsById(id)) {
			throw new RuntimeException("Aluno com ID " + id + " não existe");
		}
		autoRepository.deleteById(id);
	}

	public AutorResponse atualizar(Long id, AutorRequest dto) {
		Autor autor = autoRepository.findById(id).orElseThrow(() -> new RuntimeException("Autor nao encotrado"));
		autor.setEmail(dto.getNome());
		autor.setNome(dto.getNome());

		Autor atualizar = autoRepository.save(autor);
		return new AutorResponse(atualizar);
	}

}
