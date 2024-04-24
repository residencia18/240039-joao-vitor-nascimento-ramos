package br.com.cepedi.Voll.api.model.records.appointment.details;

import br.com.cepedi.Voll.api.model.entitys.Appointment;
import br.com.cepedi.Voll.api.model.records.appointment.enums.ReasonCancelAppointment;

import java.time.LocalDateTime;

public record DataDetailsAppointment(
        Long id,
        Long doctorId,
        String doctorName,
        Long patientId,
        String patientName,
        LocalDateTime dateService,
        ReasonCancelAppointment reasonCancel
) {
    public DataDetailsAppointment(Appointment appointment) {
        this(
                appointment.getId(),
                (appointment.getDoctor() != null ? appointment.getDoctor().getId() : null),
                (appointment.getDoctor() != null ? appointment.getDoctor().getName() : null),
                (appointment.getPatient() != null ? appointment.getPatient().getId() : null),
                (appointment.getPatient() != null ? appointment.getPatient().getName() : null),
                appointment.getDateService(),
                appointment.getReasonCancel()
        );
    }
}