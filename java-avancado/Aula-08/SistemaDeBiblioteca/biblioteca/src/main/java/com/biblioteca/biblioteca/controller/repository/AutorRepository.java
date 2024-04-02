package com.biblioteca.biblioteca.controller.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.biblioteca.biblioteca.model.Autor;


@Service
public interface AutorRepository extends JpaRepository<Autor,Long>{
	


}
