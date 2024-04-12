package br.com.cepedi.Voll.api.repository;

import br.com.cepedi.Voll.api.model.entitys.Doctor;
import br.com.cepedi.Voll.api.model.records.doctor.input.doctor.Specialty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {


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

    @Query ("""
        select d.activated
        from Doctor d
        where
        d.id = :id
        """)
    Boolean findAtivoById(Long id);

}
