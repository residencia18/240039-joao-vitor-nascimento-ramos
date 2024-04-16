package br.com.cepedi.Voll.api.model.record.doctor.input;

import br.com.cepedi.Voll.api.model.records.address.DataAddress;
import br.com.cepedi.Voll.api.model.records.doctor.input.DataRegisterDoctor;
import br.com.cepedi.Voll.api.model.records.doctor.input.Specialty;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestDataRegisterDoctor {

    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();

    @Test
    @DisplayName("Test valid DataRegisterDoctor")
    public void testValidDataRegisterDoctor() {
        DataRegisterDoctor dataRegisterDoctor = new DataRegisterDoctor(
                "Dr. John Doe",
                "johndoe@example.com",
                "123456789",
                "12345",
                Specialty.DERMATOLOGY,
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

        Set<ConstraintViolation<DataRegisterDoctor>> violations = validator.validate(dataRegisterDoctor);
        assertTrue(violations.isEmpty(), "Expected no violations, but found some.");
    }

    @Test
    @DisplayName("Test invalid DataRegisterDoctor with missing required fields")
    public void testInvalidDataRegisterDoctorMissingRequiredFields() {
        DataRegisterDoctor dataRegisterDoctor = new DataRegisterDoctor(
                null,
                null,
                null,
                null,
                null,
                null
        );

        Set<ConstraintViolation<DataRegisterDoctor>> violations = validator.validate(dataRegisterDoctor);
        assertTrue(!violations.isEmpty(), "Expected violations, but found none.");
    }

    @Test
    @DisplayName("Test invalid DataRegisterDoctor with invalid email")
    public void testInvalidDataRegisterDoctorInvalidEmail() {
        DataRegisterDoctor dataRegisterDoctor = new DataRegisterDoctor(
                "Dr. John Doe",
                "invalidemail",
                "123456789",
                "12345",
                Specialty.DERMATOLOGY,
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

        Set<ConstraintViolation<DataRegisterDoctor>> violations = validator.validate(dataRegisterDoctor);
        assertTrue(!violations.isEmpty(), "Expected violations, but found none.");
    }

}
