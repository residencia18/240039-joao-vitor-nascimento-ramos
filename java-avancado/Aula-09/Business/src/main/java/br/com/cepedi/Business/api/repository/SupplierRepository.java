package br.com.cepedi.Business.api.repository;

import br.com.cepedi.Business.api.model.entitys.Supplier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SupplierRepository extends JpaRepository<Supplier,Long> {

    Page<Supplier> findAllByActivatedTrue(Pageable pageable);


    @Query("""
            SELECT s.activated FROM Supplier s WHERE s.id = :id
            """)
    Boolean findActivatedById(Long id);

}
