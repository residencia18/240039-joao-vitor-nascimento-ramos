package com.cepedi.redesocial.model.reposity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cepedi.redesocial.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {
	
	List<Usuario> findByName(String nome);

}
