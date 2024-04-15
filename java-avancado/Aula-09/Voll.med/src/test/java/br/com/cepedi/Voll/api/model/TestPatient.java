package br.com.cepedi.Voll.api.model;

import br.com.cepedi.Voll.api.model.entitys.Patient;
import br.com.cepedi.Voll.api.model.records.address.DataAddress;
import br.com.cepedi.Voll.api.model.records.patient.input.DataRegisterPatient;
import br.com.cepedi.Voll.api.model.records.patient.input.DataUpdatePatient;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestPatient {

    @Test
    @DisplayName("Create patient from data")
    public void shouldCreatePatientFromData() {
        // Given
        DataRegisterPatient data = new DataRegisterPatient(
                "John Doe",
                "john@example.com",
                "123456789",
                "123.456.789-10",
                new DataAddress("123 Main St", "City", "12345", "State", "XX", null, null)
        );

        // When
        Patient patient = new Patient(data);

        // Then
        assertNotNull(patient);
        assertEquals("John Doe", patient.getName());
        assertEquals("john@example.com", patient.getEmail());
        assertEquals("123456789", patient.getPhoneNumber());
        assertEquals("123.456.789-10", patient.getCpf());
        assertEquals(true, patient.getActivated());
        assertNotNull(patient.getAddress());
    }

    @Test
    @DisplayName("Update patient data")
    public void shouldUpdatePatientData() {
        // Given
        Patient patient = new Patient();
        DataUpdatePatient data = new DataUpdatePatient(
                1L,
                "Jane Smith",
                "987654321",
                new DataAddress("456 Oak St", "Town", "54321888", "State", "YY", null, null)
        );

        // When
        patient.updateData(data);

        // Then
        assertEquals("Jane Smith", patient.getName());
        assertEquals("987654321", patient.getPhoneNumber());
        assertEquals("456 Oak St", patient.getAddress().getPublicPlace());
        assertEquals("Town", patient.getAddress().getNeighborhood());
        assertEquals("54321888", patient.getAddress().getCep());
        assertEquals("State", patient.getAddress().getCity());
        assertEquals("YY", patient.getAddress().getUf());
    }

    @Test
    @DisplayName("Logical delete patient")
    public void shouldLogicalDeletePatient() {
        // Given
        Patient patient = new Patient();

        // When
        patient.logicalDelete();

        // Then
        assertEquals(false, patient.getActivated());
    }
}
