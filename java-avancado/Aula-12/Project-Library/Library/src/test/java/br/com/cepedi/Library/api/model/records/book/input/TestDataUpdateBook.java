package br.com.cepedi.Library.api.model.records.book.input;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@DisplayName("Tests for DataUpdateBook class")
public class TestDataUpdateBook {

    @Test
    @DisplayName("Test constructor with valid data")
    void testConstructorWithValidData() {
        // Given: Valid book data
        Long id = 1L;
        String name = "Updated Book Name";
        Integer anoPublicacao = 2023;
        Long authorId = 2L;
        Long publisherId = 2L;

        // When: We create an instance of DataUpdateBook with valid data
        DataUpdateBook dataUpdateBook = new DataUpdateBook(id, name, anoPublicacao, authorId, publisherId);

        // Then: We verify if the values were correctly assigned
        assertEquals(id, dataUpdateBook.id());
        assertEquals(name, dataUpdateBook.name());
        assertEquals(anoPublicacao, dataUpdateBook.anoPublicacao());
        assertEquals(authorId, dataUpdateBook.authorId());
        assertEquals(publisherId, dataUpdateBook.publisherId());
    }

    @Test
    @DisplayName("Test constructor with null ID")
    void testConstructorWithNullId() {
        // Given: Book data with null ID
        Long id = null;
        String name = "Updated Book Name";
        Integer anoPublicacao = 2023;
        Long authorId = 2L;
        Long publisherId = 2L;

        // When: We create an instance of DataUpdateBook with null ID
        DataUpdateBook dataUpdateBook = new DataUpdateBook(id, name, anoPublicacao, authorId, publisherId);

        // Then: We verify if the ID is null and other values were correctly assigned
        assertNull(dataUpdateBook.id());
        assertEquals(name, dataUpdateBook.name());
        assertEquals(anoPublicacao, dataUpdateBook.anoPublicacao());
        assertEquals(authorId, dataUpdateBook.authorId());
        assertEquals(publisherId, dataUpdateBook.publisherId());
    }
}