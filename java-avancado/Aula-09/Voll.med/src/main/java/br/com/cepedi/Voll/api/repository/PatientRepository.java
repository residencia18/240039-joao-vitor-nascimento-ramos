package br.com.cepedi.Voll.api.repository;

import br.com.cepedi.Voll.api.model.entitys.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient,Long> {
}
