package br.com.cepedi.Voll.api.model;

import br.com.cepedi.Voll.api.model.entitys.Appointment;
import br.com.cepedi.Voll.api.model.entitys.Doctor;
import br.com.cepedi.Voll.api.model.entitys.Patient;
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

    @Test
    @DisplayName("Test getters and setters")
    public void testGettersAndSetters() {
        // Given
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

        // Test getters
        assertEquals(1L, appointment.getId());
        assertEquals(doctor, appointment.getDoctor());
        assertEquals(patient, appointment.getPatient());
        assertEquals(dateService, appointment.getDateService());
        assertEquals(ReasonCancelAppointment.DOCTOR_CANCEL, appointment.getReasonCancel());

        // Test setters
        Doctor newDoctor = new Doctor();
        newDoctor.setId(3L);
        newDoctor.setName("Dr. Jane Smith");
        appointment.setDoctor(newDoctor);

        Patient newPatient = new Patient();
        newPatient.setId(4L);
        newPatient.setName("John Doe");
        appointment.setPatient(newPatient);

        LocalDateTime newDateService = LocalDateTime.of(2024, 4, 16, 14, 45);
        appointment.setDateService(newDateService);

        ReasonCancelAppointment newReasonCancel = ReasonCancelAppointment.PATIENT_GIVE_UP;
        appointment.setReasonCancel(newReasonCancel);

        // Verify changes
        assertEquals(newDoctor, appointment.getDoctor());
        assertEquals(newPatient, appointment.getPatient());
        assertEquals(newDateService, appointment.getDateService());
        assertEquals(newReasonCancel, appointment.getReasonCancel());
    }

    @Test
    @DisplayName("Test toString")
    public void testToString() {
        // Given
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

        // Verify toString
        String expectedToString = "Appointment(id=1, doctor=Doctor(id=1, name=Dr. John Doe), patient=Patient(id=2, name=Jane Smith), dateService=2024-04-15T10:30, reasonCancel=DOCTOR_CANCEL)";
        assertEquals(expectedToString, appointment.toString());
    }

    @Test
    @DisplayName("Test hashCode and equals")
    public void testHashCodeAndEquals() {
        // Given
        Doctor doctor = new Doctor();
        doctor.setId(1L);
        doctor.setName("Dr. John Doe");

        Patient patient = new Patient();
        patient.setId(2L);
        patient.setName("Jane Smith");

        LocalDateTime dateService = LocalDateTime.of(2024, 4, 15, 10, 30);

        // Create two appointments with the same data
        Appointment appointment1 = new Appointment();
        appointment1.setId(1L);
        appointment1.setDoctor(doctor);
        appointment1.setPatient(patient);
        appointment1.setDateService(dateService);
        appointment1.setReasonCancel(ReasonCancelAppointment.DOCTOR_CANCEL);

        Appointment appointment2 = new Appointment();
        appointment2.setId(1L);
        appointment2.setDoctor(doctor);
        appointment2.setPatient(patient);
        appointment2.setDateService(dateService);
        appointment2.setReasonCancel(ReasonCancelAppointment.DOCTOR_CANCEL);

        // Test hashCode and equals
        assertEquals(appointment1.hashCode(), appointment2.hashCode());
        assertEquals(appointment1, appointment2);
    }

}
