package br.com.cepedi.Library.api.model.records.publisher.input;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests for DataRegisterPublisher")
public class TestDataRegisterPublisher {

    @Test
    @DisplayName("Test constructor and getter")
    void testConstructorAndGetters() {
        // Given: Publisher name
        String publisherName = "Publisher Name";

        // When: We create an instance of DataRegisterPublisher
        DataRegisterPublisher publisher = new DataRegisterPublisher(publisherName);

        // Then: We verify if the constructor and getter work correctly
        assertEquals(publisherName, publisher.name());
    }

    @Test
    @DisplayName("Test validation with valid data")
    void testValidationWithValidData() {
        // Given: Valid Publisher data
        DataRegisterPublisher publisher = new DataRegisterPublisher("Valid Publisher");

        // When: We validate the data
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<DataRegisterPublisher>> violations = validator.validate(publisher);

        // Then: We verify that there are no violations
        assertTrue(violations.isEmpty());
    }

    @Test
    @DisplayName("Test validation with blank name")
    void testValidationWithBlankName() {
        // Given: Publisher with blank name
        DataRegisterPublisher publisher = new DataRegisterPublisher("");

        // When: We validate the data
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<DataRegisterPublisher>> violations = validator.validate(publisher);

        // Then: We verify that there is a violation for the blank name
        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
        ConstraintViolation<DataRegisterPublisher> violation = violations.iterator().next();
        assertEquals("Name is required", violation.getMessage());
    }
}
