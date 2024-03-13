package br.com.cepedi.petshop.controller.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cepedi.petshop.model.Marca;

public interface MarcaRepository extends JpaRepository<Marca,Long> {

}
