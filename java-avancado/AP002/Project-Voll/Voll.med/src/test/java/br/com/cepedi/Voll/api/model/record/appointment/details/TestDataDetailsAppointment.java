package br.com.cepedi.Voll.api.model.record.appointment.details;

import br.com.cepedi.Voll.api.model.entitys.Appointment;
import br.com.cepedi.Voll.api.model.entitys.Doctor;
import br.com.cepedi.Voll.api.model.entitys.Patient;
import br.com.cepedi.Voll.api.model.records.appointment.details.DataDetailsAppointment;
import br.com.cepedi.Voll.api.model.records.appointment.enums.ReasonCancelAppointment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestDataDetailsAppointment {

    @Test
    @DisplayName("Test DataDetailsAppointment Constructor")
    public void testDataDetailsAppointmentConstructor() {

        Doctor doctor = new Doctor();
        doctor.setId(1L);
        doctor.setName("Dr. John Doe");

        Patient patient = new Patient();
        patient.setId(2L);
        patient.setName("Jane Smith");

        LocalDateTime dateService = LocalDateTime.of(2024, 4, 15, 10, 30);

        // Create an appointment
        Appointment appointment = new Appointment();
        appointment.setId(1L);
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        appointment.setDateService(dateService);
        appointment.setReasonCancel(ReasonCancelAppointment.DOCTOR_CANCEL);

        // Create a DataDetailsAppointment object using the constructor that takes an Appointment object
        DataDetailsAppointment detailsAppointment = new DataDetailsAppointment(appointment);

        // Verify if the object was created correctly
        assertNotNull(detailsAppointment);
        assertEquals(appointment.getId(), detailsAppointment.id());
        assertEquals(doctor.getId(), detailsAppointment.doctorId());
        assertEquals(doctor.getName(), detailsAppointment.doctorName());
        assertEquals(patient.getId(), detailsAppointment.patientId());
        assertEquals(patient.getName(), detailsAppointment.patientName());
        assertEquals(appointment.getDateService(), detailsAppointment.dateService());
        assertEquals(appointment.getReasonCancel(), detailsAppointment.reasonCancel());
    }
}
