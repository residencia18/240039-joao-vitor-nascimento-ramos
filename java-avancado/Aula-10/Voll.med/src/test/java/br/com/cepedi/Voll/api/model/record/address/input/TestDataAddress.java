package br.com.cepedi.Voll.api.model.record.address.input;

import br.com.cepedi.Voll.api.model.records.address.DataAddress;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestDataAddress {


    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();

    @Test
    @DisplayName("Test valid DataAddress")
    public void testValidDataAddress() {
        DataAddress dataAddress = new DataAddress(
                "Rua Teste",
                "Bairro Teste",
                "12345678",
                "City Test",
                "UF Test",
                "Complement Test",
                "123"
        );

        Set<ConstraintViolation<DataAddress>> violations = validator.validate(dataAddress);
        assertTrue(violations.isEmpty(), "Expected no violations, but found some.");
    }

    @Test
    @DisplayName("Test invalid DataAddress with missing required fields")
    public void testInvalidDataAddressMissingRequiredFields() {
        DataAddress dataAddress = new DataAddress(
                null,
                null,
                null,
                null,
                null,
                null,
                null
        );

        Set<ConstraintViolation<DataAddress>> violations = validator.validate(dataAddress);
        assertTrue(!violations.isEmpty(), "Expected violations, but found none.");
    }

    @Test
    @DisplayName("Test invalid DataAddress with invalid CEP")
    public void testInvalidDataAddressInvalidCEP() {
        DataAddress dataAddress = new DataAddress(
                "Rua Teste",
                "Bairro Teste",
                "123",
                "City Test",
                "UF Test",
                "Complement Test",
                "123"
        );

        Set<ConstraintViolation<DataAddress>> violations = validator.validate(dataAddress);
        assertTrue(!violations.isEmpty(), "Expected violations, but found none.");
    }


}
