package br.com.cepedi.Business.api.repository;

import br.com.cepedi.Business.api.model.entitys.SaleProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleProductRepository extends JpaRepository<SaleProduct,Long> {
}
