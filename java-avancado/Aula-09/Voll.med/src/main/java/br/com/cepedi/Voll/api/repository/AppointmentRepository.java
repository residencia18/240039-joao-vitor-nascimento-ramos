package br.com.cepedi.Voll.api.repository;

import br.com.cepedi.Voll.api.model.entitys.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface AppointmentRepository extends JpaRepository<Appointment,Long> {


    @Query("""
            SELECT CASE WHEN COUNT(a) > 0 THEN TRUE ELSE FALSE END 
            FROM Appointment a 
            WHERE a.patient.id = :id 
            AND a.dateService BETWEEN :startDate AND :endDate AND a.reasonCancel is null
            """)
    Boolean existsByPatientIdAndDataBetween(Long id, LocalDateTime startDate, LocalDateTime endDate);

}
