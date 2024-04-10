package br.com.cepedi.Voll.api.repository;

import br.com.cepedi.Voll.api.model.entitys.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient,Long> {
    Page<Patient> findAllByActivatedTrue(Pageable pageable);
}
