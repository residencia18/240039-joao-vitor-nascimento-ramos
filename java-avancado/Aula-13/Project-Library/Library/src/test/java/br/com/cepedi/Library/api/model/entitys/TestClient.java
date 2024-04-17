package br.com.cepedi.Library.api.model.entitys;

import br.com.cepedi.Library.api.model.embeddables.Address;
import br.com.cepedi.Library.api.model.records.address.input.DataRegisterAddress;
import br.com.cepedi.Library.api.model.records.client.input.DataRegisterClient;
import br.com.cepedi.Library.api.model.records.client.input.DataUpdateClient;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests for the Client entity")
public class TestClient {

    @Test
    @DisplayName("Test constructor with all parameters")
    void testConstructorWithAllParameters() {
        // Given: Client data
        Long id = 1L;
        String name = "John Doe";
        String email = "john.doe@example.com";
        String cpf = "123.456.789-00";
        String phoneNumber = "123456789";
        Boolean activated = true;
        DataRegisterAddress dataRegisterAddress = new DataRegisterAddress(
                "Street", "bairro", "12345678", "city", "BA", "complement","123");        List<Loan> loans = new ArrayList<>(); // Assuming Loan is another entity
        Address address = new Address(dataRegisterAddress);
        // When: We create an instance of Client using constructor with all parameters
        Client client = new Client(id, name, email, cpf, phoneNumber, activated, address, loans);

        // Then: We verify if the values were correctly assigned
        assertEquals(id, client.getId());
        assertEquals(name, client.getName());
        assertEquals(email, client.getEmail());
        assertEquals(cpf, client.getCpf());
        assertEquals(phoneNumber, client.getPhoneNumber());
        assertEquals(activated, client.getActivated());
        assertEquals(address, client.getAddress());
        assertEquals(loans, client.getLoans());
    }

    @Test
    @DisplayName("Test updateData method")
    void testUpdateData() {
        // Given: Client data
        Client client = new Client();
        DataUpdateClient dataUpdateClient = new DataUpdateClient(
                1L, "Jane Doe", "jane.doe@example.com", "987654321", "987.654.321-00", null);

        // When: We update client data
        client.updateData(dataUpdateClient);

        // Then: We verify if the data was updated correctly
        assertEquals("Jane Doe", client.getName());
        assertEquals("jane.doe@example.com", client.getEmail());
        assertEquals("987654321", client.getPhoneNumber());
        assertEquals("987.654.321-00", client.getCpf());
    }


    @Test
    @DisplayName("Test getters and setters")
    void testGettersAndSetters() {
        // Given: Client data
        Client client = new Client();
        client.setId(1L);
        client.setName("John Doe");
        client.setEmail("john.doe@example.com");
        client.setCpf("123.456.789-00");
        client.setPhoneNumber("123456789");
        client.setActivated(true);
        DataRegisterAddress dataRegisterAddress = new DataRegisterAddress(
                "Street", "bairro", "12345678", "city", "BA", "complement","123");
        Address address = new Address(dataRegisterAddress);
        client.setAddress(address);
        List<Loan> loans = new ArrayList<>(); // Assuming Loan is another entity
        client.setLoans(loans);

        // Then: We verify if getters return correct values
        assertEquals(1L, client.getId());
        assertEquals("John Doe", client.getName());
        assertEquals("john.doe@example.com", client.getEmail());
        assertEquals("123.456.789-00", client.getCpf());
        assertEquals("123456789", client.getPhoneNumber());
        assertTrue(client.getActivated());
        assertEquals(address, client.getAddress());
        assertEquals(loans, client.getLoans());
    }

    @Test
    @DisplayName("Test toString method")
    void testToString() {
        // Given: Client data
        DataRegisterAddress dataRegisterAddress = new DataRegisterAddress(
                "Street", "bairro", "12345678", "city", "BA", "complement","123");
        Address address = new Address(dataRegisterAddress);
        Client client = new Client(1L, "John Doe", "john.doe@example.com", "123.456.789-00",
                "123456789", true, address, new ArrayList<>());

        // Then: We verify if toString method works correctly
        assertEquals("Client(id=1, name=John Doe, email=john.doe@example.com, cpf=123.456.789-00, phoneNumber=123456789, activated=true, " +
                        "address=Address(publicPlace=Street, neighborhood=bairro, cep=12345678, city=city, uf=BA, complement=complement, number=123), loans=[])",
                client.toString());
    }

    @Test
    @DisplayName("Test equals method")
    void testEquals() {
        // Given: Client data
        DataRegisterAddress dataRegisterAddress1 = new DataRegisterAddress(
                "Street", "bairro", "12345678", "city", "BA", "complement","123");
        DataRegisterAddress dataRegisterAddress2 = new DataRegisterAddress(
                "Street", "bairro", "12345678", "city", "BA", "complement","123");

        Client client1 = new Client(1L, "John Doe", "john.doe@example.com", "123.456.789-00",
                "123456789", true, new Address(dataRegisterAddress1), new ArrayList<>());
        Client client2 = new Client(1L, "John Doe", "john.doe@example.com", "123.456.789-00",
                "123456789", true, new Address(dataRegisterAddress2), new ArrayList<>());
        Client client3 = new Client(2L, "Jane Doe", "jane.doe@example.com", "987.654.321-00",
                "987654321", true, new Address(dataRegisterAddress1), new ArrayList<>());

        // Then: We verify if equals method works correctly
        assertTrue(client1.equals(client2));
        assertFalse(client1.equals(client3));
    }

    @Test
    @DisplayName("Test hashCode method")
    void testHashCode() {
        // Given: Client data
        Client client1 = new Client(1L, "John Doe", "john.doe@example.com", "123.456.789-00",
                "123456789", true, new Address(new DataRegisterAddress(
                "Street", "bairro", "12345678", "city", "BA", "complement","123")), new ArrayList<>());
        Client client2 = new Client(1L, "John Doe", "john.doe@example.com", "123.456.789-00",
                "123456789", true, new Address(new DataRegisterAddress(
                "Street", "bairro", "12345678", "city", "BA", "complement","123")), new ArrayList<>());
        Client client3 = new Client(2L, "Jane Doe", "jane.doe@example.com", "987.654.321-00",
                "987654321", true, new Address(new DataRegisterAddress(
                "Street", "bairro", "12345678", "city", "BA", "complement","123")), new ArrayList<>());

        // Then: We verify if hashCode method works correctly
        assertEquals(client1.hashCode(), client2.hashCode());
        assertNotEquals(client1.hashCode(), client3.hashCode());
    }

    @Test
    @DisplayName("Test constructor with DataRegisterClient")
    void testConstructorWithDataRegisterClient() {
        // Given: DataRegisterClient data
        DataRegisterClient data = new DataRegisterClient("John Doe", "john.doe@example.com",
                "123456789", "123.456.789-00", new DataRegisterAddress(
                "Street", "bairro", "12345678", "city", "BA", "complement","123"));

        // When: Create Client using constructor with DataRegisterClient
        Client client = new Client(data);

        // Then: Verify if Client object is correctly initialized
        assertEquals("John Doe", client.getName());
        assertEquals("john.doe@example.com", client.getEmail());
        assertEquals("123456789", client.getPhoneNumber());
        assertEquals("123.456.789-00", client.getCpf());
        assertEquals(new Address(new DataRegisterAddress(
                "Street", "bairro", "12345678", "city", "BA", "complement","123")), client.getAddress());
        assertTrue(client.getActivated());
    }

//    @Test
//    @DisplayName("Test equals method between two objects")
//    void testEqualsBetweenObjects() {
//        // Given: Two Client objects with same attributes
//        Client client1 = new Client(1L, "John Doe", "john.doe@example.com", "123.456.789-00",
//                "123456789", true, new Address("Street", "City", "12345678", "State", "Complement", "123"), new ArrayList<>());
//        Client client2 = new Client(1L, "John Doe", "john.doe@example.com", "123.456.789-00",
//                "123456789", true, new Address("Street", "City", "12345678", "State", "Complement", "123"), new ArrayList<>());
//
//        // Then: Verify if equals method returns true between the two objects
//        assertTrue(client1.equals(client2));
//    }


}
