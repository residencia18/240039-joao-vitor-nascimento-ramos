package br.com.cepedi.Library.api.model.records.address.input;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests for the DataRegisterAddress class")
public class TestDataRegisterAddress {

    @Test
    @DisplayName("Test DataRegisterAddress instantiation")
    void testDataRegisterAddress() {
        // Given: Address data
        DataRegisterAddress address = new DataRegisterAddress(
                "Rua Principal",
                "Bairro Central",
                "12345678",
                "Cidade",
                "UF",
                "Complemento",
                "123"
        );

        // Then: We verify if the values were correctly assigned
        assertEquals("Rua Principal", address.publicPlace());
        assertEquals("Bairro Central", address.neighborhood());
        assertEquals("12345678", address.cep());
        assertEquals("Cidade", address.city());
        assertEquals("UF", address.uf());
        assertEquals("Complemento", address.complement());
        assertEquals("123", address.number());
    }

    @Test
    @DisplayName("Test DataRegisterAddress validation")
    void testDataRegisterAddressValidation() {
        // Given: Invalid address data
        DataRegisterAddress invalidAddress = new DataRegisterAddress(
                "", // Blank publicPlace
                "Bairro Central",
                "1234567", // Invalid cep format
                "Cidade",
                "", // Blank uf
                "Complemento",
                "123"
        );

        // When: We perform validation
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<DataRegisterAddress>> violations = validator.validate(invalidAddress);

        // Then: We verify if there are violations
        assertTrue(violations.size() > 0);
    }

}