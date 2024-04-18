package br.com.cepedi.Voll.api.model.entitys;

import br.com.cepedi.Voll.api.model.records.address.input.DataRegisterAddress;
import br.com.cepedi.Voll.api.model.records.doctor.input.DataRegisterDoctor;
import br.com.cepedi.Voll.api.model.records.doctor.input.DataUpdateDoctor;
import br.com.cepedi.Voll.api.model.records.doctor.input.Specialty;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Test entity doctor")
public class TestDoctor {

    @Test
    @DisplayName("Create Doctor")
    public void createDoctor() {
        DataRegisterDoctor data = new DataRegisterDoctor("Dr. John Doe", "john@example.com", "123456789", "12345", Specialty.DERMATOLOGY, new DataRegisterAddress("Rua Teste", "Bairro Teste", "12345-678", "City Test", "UF Test", "Complement Test", "123"));
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
        DataRegisterDoctor dataRegister = new DataRegisterDoctor("Dr. John Doe", "john@example.com", "123456789", "12345", Specialty.DERMATOLOGY, new DataRegisterAddress("Rua Teste", "Bairro Teste", "12345-678", "City Test", "UF Test", "Complement Test", "123"));
        Doctor doctor = new Doctor(dataRegister);
        DataUpdateDoctor data = new DataUpdateDoctor(1L, "Dr. Jane Doe", "jane@example.com", "987654321", new DataRegisterAddress("Rua Nova", "Novo Bairro", "87654-321", "New City", "New UF", "New Complement", "456"));
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

    @Test
    @DisplayName("Test getters, setters, and toString")
    public void testGettersSettersAndToString() {
        // Test data
        DataRegisterDoctor registerData = new DataRegisterDoctor("Dr. John Doe", "john@example.com", "123456789", "12345", Specialty.DERMATOLOGY, new DataRegisterAddress("Rua Teste", "Bairro Teste", "12345-678", "City Test", "UF Test", "Complement Test", "123"));

        // Creating a doctor from registration data
        Doctor doctor = new Doctor(registerData);

        // Test getters
        doctor.setId(1L);

        // Assertions
        assertEquals(1L, doctor.getId());
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

        // Test setters
        doctor.setName("Dr. Jane Smith");
        doctor.setEmail("jane@example.com");
        doctor.setPhoneNumber("987654321");
        doctor.setCrm("54321");
        doctor.setSpecialty(Specialty.DERMATOLOGY);
        DataRegisterAddress newAddress = new DataRegisterAddress("New St", "New Neighborhood", "54321-876", "New City", "New UF", "New Complement", "456");
        doctor.setAddress(new Address(newAddress));

        // Test toString
        String expectedToString = "Doctor(id=1, name=Dr. Jane Smith, email=jane@example.com, phoneNumber=987654321, crm=54321, specialty=DERMATOLOGY, address=Address(publicPlace=New St, neighborhood=New Neighborhood, cep=54321-876, city=New City, uf=New UF, complement=New Complement, number=456), activated=true)";
        assertEquals(expectedToString, doctor.toString());
    }

    @Test
    @DisplayName("Test equals based on ID")
    public void testEqualsBasedOnId() {
        // Creating doctors with the same ID
        Doctor doctor1 = new Doctor(1L, "Dr. John Doe", "john@example.com", "123456789", "12345", Specialty.DERMATOLOGY, new Address(new DataRegisterAddress("Rua Teste", "Bairro Teste", "12345-678", "City Test", "UF Test", "Complement Test", "123")), true);
        Doctor doctor2 = new Doctor(1L, "Dr. Jane Smith", "jane@example.com", "987654321", "54321", Specialty.DERMATOLOGY, new Address(new DataRegisterAddress("New St", "New Neighborhood", "54321-876", "New City", "New UF", "New Complement", "456")), true);

        // Creating a doctor with a different ID
        Doctor doctor3 = new Doctor(2L, "Dr. John Doe", "john@example.com", "123456789", "12345", Specialty.DERMATOLOGY, new Address(new DataRegisterAddress("Rua Teste", "Bairro Teste", "12345-678", "City Test", "UF Test", "Complement Test", "123")), true);

        // Testing equals method
        assertTrue(doctor1.equals(doctor2)); // Reflexivity
        assertTrue(doctor2.equals(doctor1)); // Symmetry
        assertFalse(doctor1.equals(doctor3)); // Different IDs
    }

    @Test
    @DisplayName("Test hashCode based on ID")
    public void testHashCodeBasedOnId() {
        // Creating doctors with the same ID
        Doctor doctor1 = new Doctor(1L, "Dr. John Doe", "john@example.com", "123456789", "12345", Specialty.DERMATOLOGY, new Address(new DataRegisterAddress("Rua Teste", "Bairro Teste", "12345-678", "City Test", "UF Test", "Complement Test", "123")), true);
        Doctor doctor2 = new Doctor(1L, "Dr. Jane Smith", "jane@example.com", "987654321", "54321", Specialty.DERMATOLOGY, new Address(new DataRegisterAddress("New St", "New Neighborhood", "54321-876", "New City", "New UF", "New Complement", "456")), true);

        // Testing hashCode method
        assertEquals(doctor1.hashCode(), doctor2.hashCode()); // Consistency
    }

    @Test
    @DisplayName("Test equals")
    public void testEquals() {
        // Creating doctors with the same ID
        Doctor doctor1 = new Doctor(1L, "Dr. John Doe", "john@example.com", "123456789", "12345", Specialty.DERMATOLOGY, new Address(new DataRegisterAddress("Rua Teste", "Bairro Teste", "12345-678", "City Test", "UF Test", "Complement Test", "123")), true);
        Doctor doctor2 = new Doctor(1L, "Dr. Jane Smith", "jane@example.com", "987654321", "54321", Specialty.DERMATOLOGY, new Address(new DataRegisterAddress("New St", "New Neighborhood", "54321-876", "New City", "New UF", "New Complement", "456")), true);

        // Testing equals method
        assertTrue(doctor1.equals(doctor2)); // Consistency
    }

}
