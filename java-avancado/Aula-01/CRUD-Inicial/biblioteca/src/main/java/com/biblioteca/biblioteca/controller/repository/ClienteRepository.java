package com.biblioteca.biblioteca.controller.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.biblioteca.biblioteca.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente,Long>{

}
