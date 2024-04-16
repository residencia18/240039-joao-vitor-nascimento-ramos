package br.com.cepedi.Voll.api.model.record.patient.input;

import br.com.cepedi.Voll.api.model.records.address.DataAddress;
import br.com.cepedi.Voll.api.model.records.patient.input.DataRegisterPatient;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestDataRegisterPatient {

    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();

    @Test
    @DisplayName("Test valid DataRegisterPatient")
    public void testValidDataRegisterPatient() {
        DataRegisterPatient dataRegisterPatient = new DataRegisterPatient(
                "John Doe",
                "johndoe@example.com",
                "123456789",
                "123.456.789-01",
                new DataAddress(
                        "Rua Teste",
                        "Bairro Teste",
                        "12345678",
                        "City Test",
                        "UF Test",
                        "Complement Test",
                        "123"
                )
        );

        Set<ConstraintViolation<DataRegisterPatient>> violations = validator.validate(dataRegisterPatient);
        assertTrue(violations.isEmpty(), "Expected no violations, but found some.");
    }

    @Test
    @DisplayName("Test invalid DataRegisterPatient with missing required fields")
    public void testInvalidDataRegisterPatientMissingRequiredFields() {
        DataRegisterPatient dataRegisterPatient = new DataRegisterPatient(
                null,
                null,
                null,
                null,
                null
        );

        Set<ConstraintViolation<DataRegisterPatient>> violations = validator.validate(dataRegisterPatient);
        assertTrue(!violations.isEmpty(), "Expected violations, but found none.");
    }

    @Test
    @DisplayName("Test invalid DataRegisterPatient with invalid email")
    public void testInvalidDataRegisterPatientInvalidEmail() {
        DataRegisterPatient dataRegisterPatient = new DataRegisterPatient(
                "John Doe",
                "invalidemail",
                "123456789",
                "123.456.789-01",
                new DataAddress(
                        "Rua Teste",
                        "Bairro Teste",
                        "12345678",
                        "City Test",
                        "UF Test",
                        "Complement Test",
                        "123"
                )
        );

        Set<ConstraintViolation<DataRegisterPatient>> violations = validator.validate(dataRegisterPatient);
        assertTrue(!violations.isEmpty(), "Expected violations, but found none.");
    }

}
