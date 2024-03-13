package br.com.cepedi.petshop.controller.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cepedi.petshop.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto,Long>{

}
