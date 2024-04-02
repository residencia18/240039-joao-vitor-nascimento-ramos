package com.biblioteca.biblioteca.controller.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.biblioteca.biblioteca.model.Livro;

public interface LivroRepository extends JpaRepository<Livro,Long>{
	
	List<Livro> findByNome(String nome);




}
