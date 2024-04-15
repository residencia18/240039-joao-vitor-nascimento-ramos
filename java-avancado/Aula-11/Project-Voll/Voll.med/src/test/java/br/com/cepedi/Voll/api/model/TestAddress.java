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

}