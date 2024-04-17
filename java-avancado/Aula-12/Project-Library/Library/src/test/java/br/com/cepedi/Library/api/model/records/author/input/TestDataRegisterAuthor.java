package br.com.cepedi.Library.api.model.records.author.input;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Tests for the DataRegisterAuthor class")
public class TestDataRegisterAuthor {


    @Test
    @DisplayName("Test DataRegisterAuthor instantiation")
    void testDataRegisterAuthor() {
        // Given: Author data
        String name = "John Doe";

        // When: We create an instance of DataRegisterAuthor
        DataRegisterAuthor author = new DataRegisterAuthor(name);

        // Then: We verify if the value was correctly assigned
        assertEquals(name, author.name());
    }

    @Test
    @DisplayName("Test DataRegisterAuthor validation")
    void testDataRegisterAuthorValidation() {
        // Given: Invalid author data
        DataRegisterAuthor invalidAuthor = new DataRegisterAuthor("");

        // When: We perform validation
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<DataRegisterAuthor>> violations = validator.validate(invalidAuthor);

        // Then: We verify if there are violations
        assertTrue(violations.size() > 0);
    }

}
