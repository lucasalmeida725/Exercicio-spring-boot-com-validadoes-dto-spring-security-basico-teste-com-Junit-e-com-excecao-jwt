package com.example.BibliotecaTeste.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.BibliotecaTeste.model.Autor;

public interface AutoRepository extends JpaRepository<Autor, Long> {

}
