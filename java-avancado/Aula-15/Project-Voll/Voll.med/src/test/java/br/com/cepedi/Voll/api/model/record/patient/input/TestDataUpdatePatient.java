package br.com.cepedi.Voll.api.model.record.patient.input;

import br.com.cepedi.Voll.api.model.records.address.input.DataRegisterAddress;
import br.com.cepedi.Voll.api.model.records.patient.input.DataUpdatePatient;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestDataUpdatePatient {


    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();

    @Test
    @DisplayName("Test valid DataUpdatePatient")
    public void testValidDataUpdatePatient() {
        DataUpdatePatient dataUpdatePatient = new DataUpdatePatient(
                1L,
                "John Doe",
                "123456789",
                new DataRegisterAddress(
                        "Rua Teste",
                        "Bairro Teste",
                        "12345678",
                        "City Test",
                        "UF Test",
                        "Complement Test",
                        "123"
                )
        );

        Set<ConstraintViolation<DataUpdatePatient>> violations = validator.validate(dataUpdatePatient);
        assertTrue(violations.isEmpty(), "Expected no violations, but found some.");
    }

    @Test
    @DisplayName("Test invalid DataUpdatePatient with null id")
    public void testInvalidDataUpdatePatientNullId() {
        DataUpdatePatient dataUpdatePatient = new DataUpdatePatient(
                null,
                "John Doe",
                "123456789",
                new DataRegisterAddress(
                        "Rua Teste",
                        "Bairro Teste",
                        "12345678",
                        "City Test",
                        "UF Test",
                        "Complement Test",
                        "123"
                )
        );

        Set<ConstraintViolation<DataUpdatePatient>> violations = validator.validate(dataUpdatePatient);
        assertTrue(!violations.isEmpty(), "Expected violations, but found none.");
    }
}
