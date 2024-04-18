package br.com.cepedi.Library.api.model.records.address.details;

import br.com.cepedi.Library.api.model.embeddables.Address;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Test DataDetailsAddress")
public class TestDataDetailsAddress {


    @Test
    @DisplayName("DataDetailsAddress Constructor Test")
    public void testDataDetailsAddressConstructor() {
        Address address = new Address("Main Street", "Downtown", "12345-678", "City", "UF", "Apartment 101", "123");
        DataDetailsAddress dataDetailsAddress = new DataDetailsAddress(address);

        assertEquals("Main Street", dataDetailsAddress.publicPlace());
        assertEquals("Downtown", dataDetailsAddress.neighborhood());
        assertEquals("12345-678", dataDetailsAddress.cep());
        assertEquals("City", dataDetailsAddress.city());
        assertEquals("UF", dataDetailsAddress.uf());
        assertEquals("Apartment 101", dataDetailsAddress.complement());
        assertEquals("123", dataDetailsAddress.number());
    }

}
