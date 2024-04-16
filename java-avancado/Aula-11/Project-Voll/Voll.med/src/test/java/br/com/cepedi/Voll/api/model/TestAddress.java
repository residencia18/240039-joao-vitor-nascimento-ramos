package br.com.cepedi.Voll.api.model;

import br.com.cepedi.Voll.api.model.entitys.Address;
import br.com.cepedi.Voll.api.model.records.address.DataAddress;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestAddress {
    @Test
    @DisplayName("Update public place")
    public void updatePublicPlace() {
        Address address = new Address();
        DataAddress data = new DataAddress("Rua Teste", "", "", "", "", "", "");
        address.updateData(data);
        assertEquals("Rua Teste", address.getPublicPlace());
    }

    @Test
    @DisplayName("Update neighborhood")
    public void updateNeighborhood() {
        Address address = new Address();
        DataAddress data = new DataAddress("", "Bairro Teste", "", "", "", "", "");
        address.updateData(data);
        assertEquals("Bairro Teste", address.getNeighborhood());
    }

    @Test
    @DisplayName("Update CEP")
    public void updateCEP() {
        Address address = new Address();
        DataAddress data = new DataAddress("", "", "12345678", "", "", "", "");
        address.updateData(data);
        assertEquals("12345678", address.getCep());
    }

    @Test
    @DisplayName("Update city")
    public void updateCity() {
        Address address = new Address();
        DataAddress data = new DataAddress("", "", "", "City Test", "", "", "");
        address.updateData(data);
        assertEquals("City Test", address.getCity());
    }

    @Test
    @DisplayName("Update UF")
    public void updateUF() {
        Address address = new Address();
        DataAddress data = new DataAddress("", "", "", "", "UF Test", "", "");
        address.updateData(data);
        assertEquals("UF Test", address.getUf());
    }

    @Test
    @DisplayName("Update complement")
    public void updateComplement() {
        Address address = new Address();
        DataAddress data = new DataAddress("", "", "", "", "", "Complement Test", "");
        address.updateData(data);
        assertEquals("Complement Test", address.getComplement());
    }

    @Test
    @DisplayName("Update number")
    public void updateNumber() {
        Address address = new Address();
        DataAddress data = new DataAddress("", "", "", "", "", "", "123");
        address.updateData(data);
        assertEquals("123", address.getNumber());
    }

    @Test
    @DisplayName("Update all fields")
    public void updateAllFields() {
        Address address = new Address();
        DataAddress data = new DataAddress("Rua Teste", "Bairro Teste", "12345678", "City Test", "UF Test", "Complement Test", "123");
        address.updateData(data);

        assertEquals("Rua Teste", address.getPublicPlace());
        assertEquals("Bairro Teste", address.getNeighborhood());
        assertEquals("12345678", address.getCep());
        assertEquals("City Test", address.getCity());
        assertEquals("UF Test", address.getUf());
        assertEquals("Complement Test", address.getComplement());
        assertEquals("123", address.getNumber());
    }

    @Test
    @DisplayName("Update with null data")
    public void updateWithNullData() {
        Address address = new Address();
        DataAddress data = new DataAddress(null, null, null, null, null, null, null);
        address.updateData(data);

        assertEquals(null, address.getPublicPlace());
        assertEquals(null, address.getNeighborhood());
        assertEquals(null, address.getCep());
        assertEquals(null, address.getCity());
        assertEquals(null, address.getUf());
        assertEquals(null, address.getComplement());
        assertEquals(null, address.getNumber());
    }

    @Test
    @DisplayName("Update with empty data")
    public void updateWithEmptyData() {
        Address address = new Address();
        DataAddress data = new DataAddress("", "", "", "", "", "", "");
        address.updateData(data);

        assertEquals("", address.getPublicPlace());
        assertEquals("", address.getNeighborhood());
        assertEquals("", address.getCep());
        assertEquals("", address.getCity());
        assertEquals("", address.getUf());
        assertEquals("", address.getComplement());
        assertEquals("", address.getNumber());
    }

    @Test
    @DisplayName("Test the setPublicPlace method")
    public void testSetPublicPlace() {
        Address address = new Address();
        address.setPublicPlace("Rua Teste");
        assertEquals("Rua Teste", address.getPublicPlace());
    }

    @Test
    @DisplayName("Test the setNeighborhood method")
    public void testSetNeighborhood() {
        Address address = new Address();
        address.setNeighborhood("Neighborhood Test");
        assertEquals("Neighborhood Test", address.getNeighborhood());
    }

    @Test
    @DisplayName("Test the setCep method")
    public void testSetCep() {
        Address address = new Address();
        address.setCep("12345678");
        assertEquals("12345678", address.getCep());
    }

    @Test
    @DisplayName("Test the setCity method")
    public void testSetCity() {
        Address address = new Address();
        address.setCity("City Test");
        assertEquals("City Test", address.getCity());
    }

    @Test
    @DisplayName("Test the setUf method")
    public void testSetUf() {
        Address address = new Address();
        address.setUf("UF Test");
        assertEquals("UF Test", address.getUf());
    }

    @Test
    @DisplayName("Test the setComplement method")
    public void testSetComplement() {
        Address address = new Address();
        address.setComplement("Complement Test");
        assertEquals("Complement Test", address.getComplement());
    }

    @Test
    @DisplayName("Test the setNumber method")
    public void testSetNumber() {
        Address address = new Address();
        address.setNumber("123");
        assertEquals("123", address.getNumber());
    }

    @Test
    @DisplayName("Test the toString method")
    public void testToString() {
        Address address = new Address();
        address.setPublicPlace("Rua Teste");
        address.setNeighborhood("Neighborhood Test");
        address.setCep("12345678");
        address.setCity("City Test");
        address.setUf("UF Test");
        address.setComplement("Complement Test");
        address.setNumber("123");

        String expectedToString = "Address(publicPlace=Rua Teste, neighborhood=Neighborhood Test, cep=12345678, city=City Test, uf=UF Test, complement=Complement Test, number=123)";
        assertEquals(expectedToString, address.toString());
    }


}