package br.com.cepedi.petshop.controller.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cepedi.petshop.model.Venda;

public interface VendaRepository extends JpaRepository<Venda,Long>{

}
