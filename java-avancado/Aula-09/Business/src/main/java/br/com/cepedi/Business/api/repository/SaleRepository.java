package br.com.cepedi.Business.api.repository;

import br.com.cepedi.Business.api.model.entitys.Role;
import br.com.cepedi.Business.api.model.entitys.Sale;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SaleRepository extends JpaRepository<Sale,Long> {


//    Page<Sale> findAllByActivatedTrue(Pageable pageable);
//
//
//    @Query("""
//            SELECT s.activated FROM Sale s WHERE s.id = :id
//            """)
//    Boolean findActivatedById(Long id);

}
