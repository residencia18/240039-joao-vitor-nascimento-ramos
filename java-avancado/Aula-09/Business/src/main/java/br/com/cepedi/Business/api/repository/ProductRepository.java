package br.com.cepedi.Business.api.repository;

import br.com.cepedi.Business.api.model.entitys.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product,Long> {

    Page<Product> findAllByActivatedTrue(Pageable pageable);


    @Query("""
            SELECT p.activated FROM Product p WHERE p.id = :id
            """)
    Boolean findActivatedById(Long id);

}
