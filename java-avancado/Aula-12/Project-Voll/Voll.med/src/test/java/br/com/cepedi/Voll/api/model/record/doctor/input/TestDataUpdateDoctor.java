package br.com.cepedi.Voll.api.model.record.doctor.input;

import br.com.cepedi.Voll.api.model.records.address.DataAddress;
import br.com.cepedi.Voll.api.model.records.doctor.input.DataUpdateDoctor;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestDataUpdateDoctor {

    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();

    @Test
    @DisplayName("Test valid DataUpdateDoctor")
    public void testValidDataUpdateDoctor() {
        DataUpdateDoctor dataUpdateDoctor = new DataUpdateDoctor(
                1L,
                "Dr. John Doe",
                "johndoe@example.com",
                "123456789",
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

        Set<ConstraintViolation<DataUpdateDoctor>> violations = validator.validate(dataUpdateDoctor);
        assertTrue(violations.isEmpty(), "Expected no violations, but found some.");
    }

    @Test
    @DisplayName("Test invalid DataUpdateDoctor with missing required fields")
    public void testInvalidDataUpdateDoctorMissingRequiredFields() {
        DataUpdateDoctor dataUpdateDoctor = new DataUpdateDoctor(
                null,
                null,
                null,
                null,
                null
        );

        Set<ConstraintViolation<DataUpdateDoctor>> violations = validator.validate(dataUpdateDoctor);
        assertTrue(!violations.isEmpty(), "Expected violations, but found none.");
    }

    @Test
    @DisplayName("Test invalid DataUpdateDoctor with invalid email")
    public void testInvalidDataUpdateDoctorInvalidEmail() {
        DataUpdateDoctor dataUpdateDoctor = new DataUpdateDoctor(
                1L,
                "Dr. John Doe",
                "invalidemail",
                "123456789",
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

        Set<ConstraintViolation<DataUpdateDoctor>> violations = validator.validate(dataUpdateDoctor);
        assertTrue(!violations.isEmpty(), "Expected violations, but found none.");
    }

}
