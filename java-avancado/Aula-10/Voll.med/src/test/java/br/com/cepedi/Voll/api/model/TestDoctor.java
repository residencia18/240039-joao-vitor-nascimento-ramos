package br.com.cepedi.Voll.api.model;

import br.com.cepedi.Voll.api.model.entitys.Doctor;
import br.com.cepedi.Voll.api.model.records.address.DataAddress;
import br.com.cepedi.Voll.api.model.records.doctor.input.DataRegisterDoctor;
import br.com.cepedi.Voll.api.model.records.doctor.input.DataUpdateDoctor;
import br.com.cepedi.Voll.api.model.records.doctor.input.Specialty;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestDoctor {

    @Test
    @DisplayName("Create Doctor")
    public void createDoctor() {
        DataRegisterDoctor data = new DataRegisterDoctor("Dr. John Doe", "john@example.com", "123456789", "12345", Specialty.DERMATOLOGY, new DataAddress("Rua Teste", "Bairro Teste", "12345-678", "City Test", "UF Test", "Complement Test", "123"));
        Doctor doctor = new Doctor(data);
        assertEquals("Dr. John Doe", doctor.getName());
        assertEquals("john@example.com", doctor.getEmail());
        assertEquals("123456789", doctor.getPhoneNumber());
        assertEquals("12345", doctor.getCrm());
        assertEquals(Specialty.DERMATOLOGY, doctor.getSpecialty());
        assertEquals("Rua Teste", doctor.getAddress().getPublicPlace());
        assertEquals("Bairro Teste", doctor.getAddress().getNeighborhood());
        assertEquals("12345-678", doctor.getAddress().getCep());
        assertEquals("City Test", doctor.getAddress().getCity());
        assertEquals("UF Test", doctor.getAddress().getUf());
        assertEquals("Complement Test", doctor.getAddress().getComplement());
        assertEquals("123", doctor.getAddress().getNumber());
        assertTrue(doctor.getActivated());
    }

    @Test
    @DisplayName("Update Doctor")
    public void updateDoctor() {
        DataRegisterDoctor dataRegister = new DataRegisterDoctor("Dr. John Doe", "john@example.com", "123456789", "12345", Specialty.DERMATOLOGY, new DataAddress("Rua Teste", "Bairro Teste", "12345-678", "City Test", "UF Test", "Complement Test", "123"));
        Doctor doctor = new Doctor(dataRegister);
        DataUpdateDoctor data = new DataUpdateDoctor(1L, "Dr. Jane Doe", "jane@example.com", "987654321", new DataAddress("Rua Nova", "Novo Bairro", "87654-321", "New City", "New UF", "New Complement", "456"));
        doctor.updateData(data);
        assertEquals("Dr. Jane Doe", doctor.getName());
        assertEquals("987654321", doctor.getPhoneNumber());
        assertEquals("Rua Nova", doctor.getAddress().getPublicPlace());
        assertEquals("Novo Bairro", doctor.getAddress().getNeighborhood());
        assertEquals("87654-321", doctor.getAddress().getCep());
        assertEquals("New City", doctor.getAddress().getCity());
        assertEquals("New UF", doctor.getAddress().getUf());
        assertEquals("New Complement", doctor.getAddress().getComplement());
        assertEquals("456", doctor.getAddress().getNumber());
    }

    @Test
    @DisplayName("Logical Delete Doctor")
    public void logicalDeleteDoctor() {
        Doctor doctor = new Doctor();
        doctor.logicalDelete();
        assertFalse(doctor.getActivated());
    }

}
