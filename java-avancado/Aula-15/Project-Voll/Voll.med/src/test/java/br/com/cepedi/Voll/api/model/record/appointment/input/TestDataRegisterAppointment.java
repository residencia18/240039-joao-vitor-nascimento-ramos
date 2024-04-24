package br.com.cepedi.Voll.api.model.record.appointment.input;

import br.com.cepedi.Voll.api.model.records.appointment.input.DataRegisterAppointment;
import br.com.cepedi.Voll.api.model.records.doctor.input.Specialty;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestDataRegisterAppointment {

    @Test
    public void testDataRegisterAppointmentConstructor() {
        // Create a DataRegisterAppointment object
        LocalDateTime date = LocalDateTime.now().plusDays(1); // Uma data futura
        DataRegisterAppointment appointment = new DataRegisterAppointment(
                1L, // ID do médico
                2L, // ID do paciente
                date, // Data futura
                Specialty.DERMATOLOGY
        );

        // Check if the DataRegisterAppointment object was constructed correctly
        assertNotNull(appointment);
        assertEquals(1L, appointment.idDoctor());
        assertEquals(2L, appointment.idPatient());
        assertEquals(date, appointment.date());
        assertEquals(Specialty.DERMATOLOGY, appointment.specialty());
    }

}
