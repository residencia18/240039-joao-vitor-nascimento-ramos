package br.com.cepedi.petshop.controller.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cepedi.petshop.model.Venda_Produto;

public interface Venda_ProdutoRepository extends JpaRepository<Venda_Produto,Long>{

	
    List<Venda_Produto> findByVendaId(Long vendaId);

    
}
