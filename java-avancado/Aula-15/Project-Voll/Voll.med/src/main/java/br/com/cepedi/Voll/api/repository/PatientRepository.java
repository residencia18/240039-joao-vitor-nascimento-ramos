package br.com.cepedi.Voll.api.repository;

import br.com.cepedi.Voll.api.model.entitys.Patient;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    @Cacheable("patientsActivated")
    Page<Patient> findAllByActivatedTrue(Pageable pageable);

    @Query("""
            SELECT p.activated FROM Patient p WHERE p.id = :id
            """)
    Boolean findActivatedById(@Param("id") Long id);


    @Query("""
            SELECT p FROM Patient p WHERE p.email = :email AND p.activated = true
             """)
    Patient findByPatientByEmail(@Param("email") String email);

    @Query("""
            SELECT p FROM Patient p WHERE SUBSTRING_INDEX(p.name,' ',1) = :firstName
             """)
    Page<Patient> findByPatientsByFirstName(@Param("firstName") String firstName , Pageable pageable);

    @Query("""
            SELECT p FROM Patient p WHERE SUBSTRING_INDEX(p.name,' ',-1) = :lastName
             """)
    Page<Patient> findByPatientsByLastName(@Param("lastName") String lastName, Pageable pageable);


    @Query("""
            SELECT p FROM Patient p WHERE p.address.city = :city AND p.address.neighborhood = :neighborhood
           """)
    Page<Patient> findByPatientByCityAndNeighborhood(@Param("city") String city, @Param("neighborhood") String neighborhood, Pageable pageable);


}
