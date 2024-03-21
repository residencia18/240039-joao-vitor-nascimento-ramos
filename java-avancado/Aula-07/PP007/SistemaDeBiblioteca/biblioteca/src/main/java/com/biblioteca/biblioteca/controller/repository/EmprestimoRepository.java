package com.biblioteca.biblioteca.controller.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.biblioteca.biblioteca.model.Emprestimo;

public interface EmprestimoRepository extends JpaRepository<Emprestimo,Long>{

}
