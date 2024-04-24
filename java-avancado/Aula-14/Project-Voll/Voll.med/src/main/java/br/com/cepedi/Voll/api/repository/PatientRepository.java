package br.com.cepedi.Voll.api.repository;

import br.com.cepedi.Voll.api.model.entitys.Patient;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient,Long> {

    @Cacheable("patientsActivated")
    Page<Patient> findAllByActivatedTrue(Pageable pageable);

    @Query("""
            SELECT p.activated FROM Patient p WHERE p.id = :id
            """)
    Boolean findActivatedById(Long id);

    
}
