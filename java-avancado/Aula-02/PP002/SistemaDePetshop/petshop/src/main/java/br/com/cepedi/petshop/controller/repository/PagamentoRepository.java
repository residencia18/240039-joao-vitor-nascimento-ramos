package br.com.cepedi.petshop.controller.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cepedi.petshop.model.Pagamento;
import br.com.cepedi.petshop.model.Venda;

import java.util.Optional;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {

    Optional<Pagamento> findByVenda(Venda venda);
}
