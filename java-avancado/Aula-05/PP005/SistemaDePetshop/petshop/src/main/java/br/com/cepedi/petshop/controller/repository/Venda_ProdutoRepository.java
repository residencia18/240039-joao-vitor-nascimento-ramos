package br.com.cepedi.petshop.controller.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cepedi.petshop.model.VendaProduto;

public interface Venda_ProdutoRepository extends JpaRepository<VendaProduto,Long>{

	
    List<VendaProduto> findByVendaId(Long vendaId);

    
}
