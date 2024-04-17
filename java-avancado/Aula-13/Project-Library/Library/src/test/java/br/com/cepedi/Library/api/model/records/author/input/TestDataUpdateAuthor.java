package br.com.cepedi.Library.api.model.records.author.input;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Tests for the DataUpdateAuthor class")
public class TestDataUpdateAuthor {


    @Test
    @DisplayName("Test DataUpdateAuthor instantiation")
    void testDataUpdateAuthor() {
        // Given: Author data
        Long id = 1L;
        String name = "John Doe";

        // When: We create an instance of DataUpdateAuthor
        DataUpdateAuthor author = new DataUpdateAuthor(id, name);

        // Then: We verify if the values were correctly assigned
        assertEquals(id, author.id());
        assertEquals(name, author.name());
    }

    @Test
    @DisplayName("Test DataUpdateAuthor validation")
    void testDataUpdateAuthorValidation() {
        // Given: Invalid author data (id is null)
        DataUpdateAuthor invalidAuthor = new DataUpdateAuthor(null, "John Doe");

        // When: We perform validation
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<DataUpdateAuthor>> violations = validator.validate(invalidAuthor);

        // Then: We verify if there are violations


    }

}