package br.com.cepedi.Business.api.repository;

import br.com.cepedi.Business.api.model.entitys.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment,Long> {
}
