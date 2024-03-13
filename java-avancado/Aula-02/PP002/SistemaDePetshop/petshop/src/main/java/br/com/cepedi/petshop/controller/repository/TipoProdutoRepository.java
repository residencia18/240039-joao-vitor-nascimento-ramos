package br.com.cepedi.petshop.controller.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cepedi.petshop.model.TipoProduto;

public interface TipoProdutoRepository extends JpaRepository<TipoProduto,Long>{

}
