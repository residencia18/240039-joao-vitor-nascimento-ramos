package br.com.cepedi.Voll.api.model.entitys;

import br.com.cepedi.Voll.api.model.records.address.input.DataRegisterAddress;
import br.com.cepedi.Voll.api.model.records.patient.input.DataRegisterPatient;
import br.com.cepedi.Voll.api.model.records.patient.input.DataUpdatePatient;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Test entity patient")
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
                new DataRegisterAddress("123 Main St", "City", "12345", "State", "XX", null, null)
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
                new DataRegisterAddress("456 Oak St", "Town", "54321888", "State", "YY", null, null)
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

    @Test
    @DisplayName("Test getters, setters, and toString")
    public void testGettersSettersAndToString() {
        // Test data
        DataRegisterPatient registerData = new DataRegisterPatient(
                "John Doe",
                "john@example.com",
                "123456789",
                "123.456.789-10",
                new DataRegisterAddress("123 Main St", "City", "12345", "State", "XX", null, null)
        );

        // Create patient from registration data
        Patient patient = new Patient(registerData);

        // Test getters

        patient.setId(1L);

        // Test getters
        assertEquals(1L, patient.getId());
        assertEquals("John Doe", patient.getName());
        assertEquals("john@example.com", patient.getEmail());
        assertEquals("123456789", patient.getPhoneNumber());
        assertEquals("123.456.789-10", patient.getCpf());
        assertEquals(true, patient.getActivated());
        assertNotNull(patient.getAddress());

        // Test setters
        patient.setName("Jane Smith");
        patient.setEmail("jane@example.com");
        patient.setPhoneNumber("987654321");
        patient.setCpf("987.654.321-10");
        patient.setActivated(false);
        DataRegisterAddress newAddress = new DataRegisterAddress("456 Oak St", "Town", "54321888", "State", "YY", null, null);
        patient.setAddress(new Address((newAddress)));

        // Test toString
        String expectedToString = "Patient(id=1, name=Jane Smith, email=jane@example.com, phoneNumber=987654321, cpf=987.654.321-10, address=Address(publicPlace=456 Oak St, neighborhood=Town, cep=54321888, city=State, uf=YY, complement=null, number=null), activated=false)";
        assertEquals(expectedToString, patient.toString());
    }

    @Test
    @DisplayName("Test equals based on ID")
    public void testEqualsBasedOnId() {
        // Creating patients with the same ID
        Patient patient1 = new Patient(1L, "John Doe", "john@example.com", "123456789", "123.456.789-10", new Address(new DataRegisterAddress("123 Main St", "City", "12345", "State", "XX", "Complement", "1")), true);
        Patient patient2 = new Patient(1L, "Jane Smith", "jane@example.com", "987654321", "987.654.321-10", new Address(new DataRegisterAddress("123 Main St", "City", "12345", "State", "XX", "Complement", "1")), false);

        // Creating a patient with a different ID
        Patient patient3 = new Patient(2L, "John Doe", "john@example.com", "123456789", "123.456.789-10", new Address(new DataRegisterAddress("123 Main St", "City", "12345", "State", "XX", "Complement", "1")), true);

        // Testing equals method
        assertTrue(patient1.equals(patient2)); // Reflexivity
        assertTrue(patient2.equals(patient1)); // Symmetry
        assertFalse(patient1.equals(patient3)); // Different IDs
    }


    @Test
    @DisplayName("Test hashCode based on ID")
    public void testHashCodeBasedOnId() {
        // Creating patients with the same ID
        Patient patient1 = new Patient(1L, "John Doe", "john@example.com", "123456789", "123.456.789-10", new Address(new DataRegisterAddress("123 Main St", "City", "12345", "State", "XX", "Complement", "1")), true);
        Patient patient2 = new Patient(1L, "Jane Smith", "jane@example.com", "987654321", "987.654.321-10", new Address(new DataRegisterAddress("123 Main St", "City", "12345", "State", "XX", "Complement", "1")), false);

        // Testing hashCode method
        assertEquals(patient1.hashCode(), patient2.hashCode()); // Consistency
    }

    @Test
    @DisplayName("Test equals")
    public void testEquals() {
        // Creating patients with the same ID
        Patient patient1 = new Patient(1L, "John Doe", "john@example.com", "123456789", "123.456.789-10", new Address(new DataRegisterAddress("123 Main St", "City", "12345", "State", "XX", "Complement", "1")), true);
        Patient patient2 = new Patient(1L, "Jane Smith", "jane@example.com", "987654321", "987.654.321-10", new Address(new DataRegisterAddress("123 Main St", "City", "12345", "State", "XX", "Complement", "1")), false);

        // Testing hashCode method
        assertTrue(patient1.equals(patient2)); // Consistency
    }

    @Test
    @DisplayName("Test updateData method with null name and null address")
    public void testUpdateDataWithNullNameAndNullAddress() {
        // Creating a patient with initial data
        Patient patient = new Patient(1L, "John Doe", "john@example.com", "123456789", "123.456.789-10", new Address(new DataRegisterAddress("123 Main St", "City", "12345", "City", "XX", "Complement", "1")), true);

        // Creating DataUpdatePatient with null name and null dataAddress
        DataUpdatePatient data = new DataUpdatePatient(1L,null,null,null);

        // Updating patient data
        patient.updateData(data);

        // Verifying if name and address remained unchanged
        assertEquals("John Doe", patient.getName());
        assertEquals("123456789", patient.getPhoneNumber());
        assertEquals("123 Main St", patient.getAddress().getPublicPlace());
        assertEquals("City", patient.getAddress().getCity());
        assertEquals("12345", patient.getAddress().getCep());
        assertEquals("XX", patient.getAddress().getUf());
        assertEquals("Complement", patient.getAddress().getComplement());
        assertEquals("1", patient.getAddress().getNumber());
    }

    @Test
    @DisplayName("Test equals: Different IDs should return false")
    public void testEqualsFalse() {
        // Creating patients with different IDs
        Patient patient1 = new Patient(1L, "John Doe", "john@example.com", "123456789", "123.456.789-10", new Address(new DataRegisterAddress("123 Main St", "City", "12345", "State", "XX", "Complement", "1")), true);
        Patient patient2 = new Patient(2L, "John Doe", "john@example.com", "123456789", "123.456.789-10", new Address(new DataRegisterAddress("123 Main St", "City", "12345", "State", "XX", "Complement", "1")), true);

        // Testing equals method
        assertFalse(patient1.equals(patient2)); // Different IDs should return false
    }

}
