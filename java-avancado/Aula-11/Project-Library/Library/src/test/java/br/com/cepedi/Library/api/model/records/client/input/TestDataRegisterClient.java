package br.com.cepedi.Library.api.model.records.client.input;

import br.com.cepedi.Library.api.model.records.address.input.DataRegisterAddress;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Tests for the DataRegisterClient record")
public class TestDataRegisterClient {

    @Test
    @DisplayName("Test DataRegisterClient instantiation")
    void testDataRegisterClientInstantiation() {
        // Given: Client data
        String name = "John Doe";
        String email = "johndoe@example.com";
        String phoneNumber = "123456789";
        String cpf = "123.456.789-00";
        DataRegisterAddress address = new DataRegisterAddress(
                "Main Street",
                "Central District",
                "12345678",
                "City",
                "State",
                "Apartment 1",
                "123"
        );

        // When: We create an instance of DataRegisterClient
        DataRegisterClient client = new DataRegisterClient(name, email, phoneNumber, cpf, address);

        // Then: We verify if the values were correctly assigned
        assertEquals(name, client.name());
        assertEquals(email, client.email());
        assertEquals(phoneNumber, client.phoneNumber());
        assertEquals(cpf, client.cpf());
        assertEquals(address, client.dataRegisterAddress());
    }
}
