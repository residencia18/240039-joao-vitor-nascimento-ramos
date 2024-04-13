package br.com.cepedi.Business.api.repository;

import br.com.cepedi.Business.api.model.entitys.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ClientRepository extends JpaRepository<Client,Long> {

    Page<Client> findAllByActivatedTrue(Pageable pageable);


    @Query("""
            SELECT c.activated FROM Client c WHERE c.id = :id
            """)
    Boolean findActivatedById(Long id);

}
