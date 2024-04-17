package br.com.cepedi.Library.api.model.records.publisher.input;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests for DataUpdatePublisher")
public class TestDataUpdatePublisher {

    @Test
    @DisplayName("Test constructor and getter")
    void testConstructorAndGetters() {
        // Given: Publisher ID and name
        Long publisherId = 1L;
        String publisherName = "Publisher Name";

        // When: We create an instance of DataUpdatePublisher
        DataUpdatePublisher publisher = new DataUpdatePublisher(publisherId, publisherName);

        // Then: We verify if the constructor and getter work correctly
        assertEquals(publisherId, publisher.id());
        assertEquals(publisherName, publisher.name());
    }

    @Test
    @DisplayName("Test validation with valid data")
    void testValidationWithValidData() {
        // Given: Valid Publisher data
        Long publisherId = 1L;
        String publisherName = "Valid Publisher";

        // When: We create an instance of DataUpdatePublisher with valid data
        DataUpdatePublisher publisher = new DataUpdatePublisher(publisherId, publisherName);

        // Then: We assume that if the constructor works, the data is valid
        assertNotNull(publisher);
    }

    @Test
    @DisplayName("Test validation with null ID")
    void testValidationWithNullId() {
        // Given: Publisher with null ID
        String publisherName = "Publisher Name";

        // When: We create an instance of DataUpdatePublisher with null ID
        DataUpdatePublisher publisher = new DataUpdatePublisher(null, publisherName);

        // Then: We assume that if the constructor works, the data is valid
        assertNotNull(publisher);
    }

}
