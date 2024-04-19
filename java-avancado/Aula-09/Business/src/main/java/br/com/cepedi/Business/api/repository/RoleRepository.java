package br.com.cepedi.Business.api.repository;

import br.com.cepedi.Business.api.model.entitys.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {


    Page<Role> findAllByActivatedTrue(Pageable pageable);


    @Query("""
            SELECT r.activated FROM Role r WHERE r.id = :id
            """)
    Boolean findActivatedById(Long id);
}
