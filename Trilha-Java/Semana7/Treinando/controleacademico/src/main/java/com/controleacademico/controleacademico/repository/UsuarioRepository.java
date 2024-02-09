package com.controleacademico.controleacademico.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.controleacademico.controleacademico.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {

}
