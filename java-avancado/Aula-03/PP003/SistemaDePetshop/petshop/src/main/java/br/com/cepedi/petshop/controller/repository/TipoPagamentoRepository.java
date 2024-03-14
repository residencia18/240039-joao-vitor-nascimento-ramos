package br.com.cepedi.petshop.controller.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cepedi.petshop.model.TipoPagamento;

public interface TipoPagamentoRepository extends JpaRepository<TipoPagamento,Long> {

}
