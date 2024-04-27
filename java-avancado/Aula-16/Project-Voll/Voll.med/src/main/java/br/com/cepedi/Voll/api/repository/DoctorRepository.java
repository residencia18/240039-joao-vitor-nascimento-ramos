package br.com.cepedi.Voll.api.repository;

import br.com.cepedi.Voll.api.model.entitys.Doctor;
import br.com.cepedi.Voll.api.model.records.doctor.input.Specialty;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    @Cacheable("doctorsActivated")
    Page<Doctor> findAllByActivatedTrue(Pageable pageable);

    @Query("""
        SELECT d FROM Doctor d
        WHERE d.activated = true AND d.specialty = :specialty
        AND d.id NOT IN (
            SELECT a.doctor.id FROM Appointment a WHERE a.dateService = :date
        )
        ORDER BY rand() LIMIT 1
                        """)
    Doctor chooseDoctorRandomFreethisDate(Specialty specialty , LocalDateTime date);

    @Query("""
            SELECT d.activated from Doctor d
                WHERE d.id = :id
            """)

    Boolean findActivatedById(Long id);

    @Query("""
            SELECT CASE WHEN COUNT(d) > 0 THEN TRUE ELSE FALSE END
            FROM Doctor d
            WHERE d.id = :id
            AND EXISTS (
                SELECT 1 FROM Appointment a 
                WHERE a.doctor.id = :id AND a.dateService = :date
            )
            """)
    Boolean existsByDoctorIdAndData(Long id, LocalDateTime date);


    @Query("""
            SELECT p FROM Doctor p WHERE p.email = :email AND p.activated = true
             """)
    Doctor findByDoctorByEmail(@Param("email") String email);

    @Query("""
            SELECT p FROM Doctor p WHERE SUBSTRING_INDEX(p.name,' ',1) = :firstName
             """)
    Page<Doctor> findByDoctorsByFirstName(@Param("firstName") String firstName , Pageable pageable);

    @Query("""
            SELECT p FROM Doctor p WHERE SUBSTRING_INDEX(p.name,' ',-1) = :lastName
             """)
    Page<Doctor> findByDoctorsByLastName(@Param("lastName") String lastName, Pageable pageable);


    @Query("""
            SELECT p FROM Doctor p WHERE p.address.city = :city AND p.address.neighborhood = :neighborhood
           """)
    Page<Doctor> findByDoctorByCityAndNeighborhood(@Param("city") String city, @Param("neighborhood") String neighborhood, Pageable pageable);



}
