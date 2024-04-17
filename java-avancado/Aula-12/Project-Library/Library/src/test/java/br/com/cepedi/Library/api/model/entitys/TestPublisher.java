package br.com.cepedi.Library.api.model.entitys;

import br.com.cepedi.Library.api.model.records.publisher.input.DataRegisterPublisher;
import br.com.cepedi.Library.api.model.records.publisher.input.DataUpdatePublisher;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@DisplayName("Tests for Publisher class")
public class TestPublisher {

    @Test
    @DisplayName("Test constructor and getters")
    void testConstructorAndGetters() {
        // Given: Publisher ID and name
        Long publisherId = 1L;
        String publisherName = "Publisher Name";

        // When: We create an instance of Publisher
        Publisher publisher = new Publisher(publisherId, publisherName, true, new ArrayList<>());

        // Then: We verify if the constructor and getters work correctly
        assertEquals(publisherId, publisher.getId());
        assertEquals(publisherName, publisher.getName());
        assertTrue(publisher.getActivated());
        assertTrue(publisher.getBooks().isEmpty());
    }

    @Test
    @DisplayName("Test setters")
    void testSetters() {
        // Given: Publisher
        Publisher publisher = new Publisher();

        // When: We set values using setters
        Long publisherId = 1L;
        String publisherName = "Publisher Name";
        Boolean activated = true;
        List<Book> books = new ArrayList<>();
        publisher.setId(publisherId);
        publisher.setName(publisherName);
        publisher.setActivated(activated);
        publisher.setBooks(books);

        // Then: We verify if the setters work correctly
        assertEquals(publisherId, publisher.getId());
        assertEquals(publisherName, publisher.getName());
        assertEquals(activated, publisher.getActivated());
        assertEquals(books, publisher.getBooks());
    }

    @Test
    @DisplayName("Test toString method")
    void testToString() {
        // Given: Publisher data
        Long publisherId = 1L;
        String publisherName = "Publisher Name";
        Boolean activated = true;
        List<Book> books = new ArrayList<>();
        Publisher publisher = new Publisher(publisherId, publisherName, activated, books);

        // Then: We verify if the toString method works correctly
        assertEquals("Publisher(id=1, name=Publisher Name, activated=true, books=[])", publisher.toString());
    }

    @Test
    @DisplayName("Test hashCode method")
    void testHashCode() {
        // Given: Publisher data
        Long publisherId = 1L;
        String publisherName = "Publisher Name";
        Boolean activated = true;
        List<Book> books = new ArrayList<>();
        Publisher publisher1 = new Publisher(publisherId, publisherName, activated, books);
        Publisher publisher2 = new Publisher(publisherId, publisherName, activated, books);

        // Then: We verify if the hashCode method works correctly
        assertEquals(publisher1.hashCode(), publisher2.hashCode());
    }

    @Test
    @DisplayName("Test equals method")
    void testEquals() {
        // Given: Publisher data
        Long publisherId = 1L;
        String publisherName = "Publisher Name";
        Boolean activated = true;
        List<Book> books = new ArrayList<>();
        Publisher publisher1 = new Publisher(publisherId, publisherName, activated, books);
        Publisher publisher2 = new Publisher(publisherId, publisherName, activated, books);
        Publisher publisher3 = new Publisher(2L, "Different Publisher", activated, books);

        // Then: We verify if the equals method works correctly
        assertTrue(publisher1.equals(publisher2));
        assertFalse(publisher1.equals(publisher3));
    }

    @Test
    @DisplayName("Test constructor with DataRegisterPublisher")
    void testConstructorWithDataRegisterPublisher() {
        // Given: Publisher registration data
        DataRegisterPublisher dataRegisterPublisher = new DataRegisterPublisher("Publisher Name");

        // When: We create an instance of Publisher using the constructor that accepts DataRegisterPublisher
        Publisher publisher = new Publisher(dataRegisterPublisher);

        // Then: We verify if the values were correctly assigned
        assertEquals(dataRegisterPublisher.name(), publisher.getName());
        assertTrue(publisher.getActivated());
    }

    @Test
    @DisplayName("Test updateData method")
    void testUpdateData() {
        // Given: Original publisher data
        Publisher originalPublisher = new Publisher(1L, "Original Publisher", true, new ArrayList<>());

        // Given: Updated publisher data
        DataUpdatePublisher updatedData = new DataUpdatePublisher(1L, "Updated Publisher");

        // When: We update the publisher data
        originalPublisher.updateData(updatedData);

        // Then: We verify if the data was updated correctly
        assertEquals("Updated Publisher", originalPublisher.getName());
    }

    @Test
    @DisplayName("Test logicalDelete method")
    void testLogicalDelete() {
        // Given: Publisher
        Publisher publisher = new Publisher(1L, "Publisher", true, new ArrayList<>());

        // When: We perform logical delete
        publisher.logicalDelete();

        // Then: We verify if the activated flag is set to false
        assertFalse(publisher.getActivated());
    }
}
