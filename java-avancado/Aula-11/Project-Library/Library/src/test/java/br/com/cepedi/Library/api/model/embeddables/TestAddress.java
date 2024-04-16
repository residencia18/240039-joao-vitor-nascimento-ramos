package br.com.cepedi.Library.api.model.embeddables;
import br.com.cepedi.Library.api.model.records.address.input.DataRegisterAddress;
import jakarta.persistence.Embeddable;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests for the Address class")
public class TestAddress {

    @Test
    @DisplayName("Instance creation and getters/setters test")
    void testAddressInstanceAndAccessors() {
        // Given: Address data
        String publicPlace = "Rua Principal";
        String neighborhood = "Bairro Central";
        String cep = "12345678";
        String city = "Cidade";
        String uf = "UF";
        String complement = "Complemento";
        String number = "123";

        // When: We create an instance of Address
        Address address = new Address();
        address.setPublicPlace(publicPlace);
        address.setNeighborhood(neighborhood);
        address.setCep(cep);
        address.setCity(city);
        address.setUf(uf);
        address.setComplement(complement);
        address.setNumber(number);

        // Then: We verify if the values were correctly assigned
        assertEquals(publicPlace, address.getPublicPlace());
        assertEquals(neighborhood, address.getNeighborhood());
        assertEquals(cep, address.getCep());
        assertEquals(city, address.getCity());
        assertEquals(uf, address.getUf());
        assertEquals(complement, address.getComplement());
        assertEquals(number, address.getNumber());
    }

    @Test
    @DisplayName("Constructor with DataRegisterAddress test")
    void testAddressConstructorWithDataRegisterAddress() {
        // Given: Address registration data
        DataRegisterAddress dataRegisterAddress = new DataRegisterAddress(
                "Rua Principal",
                "Bairro Central",
                "12345678",
                "Cidade",
                "UF",
                "Complemento",
                "123"
        );

        // When: We create an instance of Address using the constructor that accepts a DataRegisterAddress
        Address address = new Address(dataRegisterAddress);

        // Then: We verify if the values were correctly assigned
        assertEquals(dataRegisterAddress.publicPlace(), address.getPublicPlace());
        assertEquals(dataRegisterAddress.neighborhood(), address.getNeighborhood());
        assertEquals(dataRegisterAddress.cep(), address.getCep());
        assertEquals(dataRegisterAddress.city(), address.getCity());
        assertEquals(dataRegisterAddress.uf(), address.getUf());
        assertEquals(dataRegisterAddress.complement(), address.getComplement());
        assertEquals(dataRegisterAddress.number(), address.getNumber());
    }

    @Test
    @DisplayName("Embeddable annotation test")
    void testAddressIsEmbeddable() {
        // Given: An instance of Address
        Address address = new Address();

        // Then: We verify if the class is annotated with @Embeddable
        assertTrue(Address.class.isAnnotationPresent(Embeddable.class));
    }

    @Test
    @DisplayName("NoArgsConstructor annotation test")
    void testNoArgsConstructor() {
        // When: We create an instance of Address
        Address address = new Address();

        // Then: We verify if the default constructor was created
        assertNotNull(address);
    }

    @Test
    @DisplayName("AllArgsConstructor annotation test")
    void testAllArgsConstructor() {
        // Given: Address data
        String publicPlace = "Rua Principal";
        String neighborhood = "Bairro Central";
        String cep = "12345678";
        String city = "Cidade";
        String uf = "UF";
        String complement = "Complemento";
        String number = "123";

        // When: We create an instance of Address using the constructor with all arguments
        Address address = new Address(publicPlace, neighborhood, cep, city, uf, complement, number);

        // Then: We verify if the instance was created correctly
        assertNotNull(address);
    }

    @Test
    @DisplayName("ToString annotation test")
    void testToString() {
        // Given: Address data
        String publicPlace = "Rua Principal";
        String neighborhood = "Bairro Central";
        String cep = "12345678";
        String city = "Cidade";
        String uf = "UF";
        String complement = "Complemento";
        String number = "123";

        // When: We create an instance of Address
        Address address = new Address(publicPlace, neighborhood, cep, city, uf, complement, number);

        // Then: We verify if the toString method works correctly
        assertEquals("Address(publicPlace=Rua Principal, neighborhood=Bairro Central, cep=12345678, city=Cidade, uf=UF, complement=Complemento, number=123)", address.toString());
    }

    @Test
    @DisplayName("Test updateData method")
    void testUpdateData() {
        // Given: Original address data
        Address originalAddress = new Address(
                "Rua Principal",
                "Bairro Central",
                "12345678",
                "Cidade",
                "UF",
                "Complemento",
                "123"
        );

        // Given: Updated address data
        DataRegisterAddress updatedData = new DataRegisterAddress(
                "Nova Rua",
                "Novo Bairro",
                "87654321",
                "Nova Cidade",
                "NU",
                "Novo Complemento",
                "321"
        );

        // When: We update the address data
        originalAddress.updateData(updatedData);

        // Then: We verify if the data was updated correctly
        assertEquals("Nova Rua", originalAddress.getPublicPlace());
        assertEquals("Novo Bairro", originalAddress.getNeighborhood());
        assertEquals("87654321", originalAddress.getCep());
        assertEquals("Nova Cidade", originalAddress.getCity());
        assertEquals("NU", originalAddress.getUf());
        assertEquals("Novo Complemento", originalAddress.getComplement());
        assertEquals("321", originalAddress.getNumber());
    }

    @Test
    @DisplayName("Test hashCode method")
    void testHashCode() {
        // Given: Address data
        Address address1 = new Address("Rua A", "Bairro A", "12345678", "Cidade A", "UF A", "Complemento A", "123");
        Address address2 = new Address("Rua B", "Bairro B", "87654321", "Cidade B", "UF B", "Complemento B", "456");

        // Then: We verify if the hash codes are different for different instances
        assertNotEquals(address1.hashCode(), address2.hashCode());

        // Given: Another instance with the same data as address1
        Address address3 = new Address("Rua A", "Bairro A", "12345678", "Cidade A", "UF A", "Complemento A", "123");

        // Then: We verify if the hash code is the same for instances with the same data
        assertEquals(address1.hashCode(), address3.hashCode());
    }

}

