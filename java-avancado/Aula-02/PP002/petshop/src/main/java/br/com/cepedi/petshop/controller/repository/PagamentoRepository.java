package br.com.cepedi.petshop.controller.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cepedi.petshop.model.Pagamento;

public interface PagamentoRepository extends JpaRepository<Pagamento,Long>{

}
