package br.com.cepedi.Voll.api.repository;

import br.com.cepedi.Voll.api.model.entitys.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {


    Page<Doctor> findAllByActivatedTrue(Pageable pageable);
}
