package br.com.cepedi.Library.api.model.records.client.details;

import br.com.cepedi.Library.api.model.embeddables.Address;
import br.com.cepedi.Library.api.model.entitys.Client;
import br.com.cepedi.Library.api.model.records.address.details.DataDetailsAddress;
import br.com.cepedi.Library.api.model.records.address.input.DataRegisterAddress;
import br.com.cepedi.Library.api.model.records.client.input.DataRegisterClient;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestDataDetailsClient{

    @Test
    @DisplayName("DataDetailsClient Constructor Test")
    public void testDataDetailsClientConstructor() {
        DataRegisterAddress address = new DataRegisterAddress("Main Street", "Downtown", "12345-678", "City", "UF", "Apartment 101", "123");
        Client client = new Client(new DataRegisterClient(
                "John Doe", "john@example.com", "123456789", "123.456.789-10", address));

        DataDetailsClient dataDetailsClient = new DataDetailsClient(client);

        assertEquals("John Doe", dataDetailsClient.name());
        assertEquals("john@example.com", dataDetailsClient.email());
        assertEquals("123.456.789-10", dataDetailsClient.cpf());
        assertEquals("123456789", dataDetailsClient.phoneNumber());
        assertTrue(dataDetailsClient.activated());
        assertEquals("Main Street", dataDetailsClient.address().publicPlace());
        assertEquals("Downtown", dataDetailsClient.address().neighborhood());
        assertEquals("12345-678", dataDetailsClient.address().cep());
        assertEquals("City", dataDetailsClient.address().city());
        assertEquals("UF", dataDetailsClient.address().uf());
        assertEquals("Apartment 101", dataDetailsClient.address().complement());
        assertEquals("123", dataDetailsClient.address().number());
    }

}
