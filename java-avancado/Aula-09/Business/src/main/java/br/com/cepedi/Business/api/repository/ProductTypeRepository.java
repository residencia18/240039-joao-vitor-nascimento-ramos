package br.com.cepedi.Business.api.repository;

import br.com.cepedi.Business.api.model.entitys.Client;
import br.com.cepedi.Business.api.model.entitys.ProductType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductTypeRepository extends JpaRepository<ProductType,Long> {

    Page<ProductType> findAllByActivatedTrue(Pageable pageable);


    @Query("""
            SELECT t.activated FROM ProductType t WHERE t.id = :id
            """)
    Boolean findActivatedById(Long id);

}
