package br.com.cepedi.Voll.api.model;

import br.com.cepedi.Voll.api.model.entitys.Appointment;
import br.com.cepedi.Voll.api.model.records.appointment.input.ReasonCancelAppointment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TestAppointment {

    @Test
    @DisplayName("Cancel appointment")
    public void shouldCancelAppointment() {
        // Given
        Appointment appointment = new Appointment();
        appointment.setId(1L);
        appointment.setDateService(LocalDateTime.now());
        assertNull(appointment.getReasonCancel());

        // When
        appointment.cancel(ReasonCancelAppointment.DOCTOR_CANCEL);

        // Then
        assertEquals(ReasonCancelAppointment.DOCTOR_CANCEL, appointment.getReasonCancel());
    }

}
