package br.com.cepedi.Library.api.model.entitys;

import br.com.cepedi.Library.api.model.records.author.input.DataRegisterAuthor;
import br.com.cepedi.Library.api.model.records.author.input.DataUpdateAuthor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@DisplayName("Tests for the Author entity")
public class TestAuthor {

    @Test
    @DisplayName("Test Author instantiation from DataRegisterAuthor")
    void testAuthorInstantiationFromDataRegisterAuthor() {
        // Given: Author data
        DataRegisterAuthor authorData = new DataRegisterAuthor("John Doe");

        // When: We create an instance of Author from DataRegisterAuthor
        Author author = new Author(authorData);

        // Then: We verify if the values were correctly assigned
        assertEquals("John Doe", author.getName());
        assertTrue(author.getActivated());
    }

    @Test
    @DisplayName("Test updateData method in Author")
    void testUpdateDataInAuthor() {
        // Given: Original author data
        Author author = new Author();
        author.setName("Jane Doe");
        author.setActivated(true);

        // Given: Updated author data
        DataUpdateAuthor updatedAuthorData = new DataUpdateAuthor(1L, "Janet Doe");

        // When: We update the author data
        author.updateData(updatedAuthorData);

        // Then: We verify if the data was updated correctly
        assertEquals("Janet Doe", author.getName());
    }

    @Test
    @DisplayName("Test logicalDelete method in Author")
    void testLogicalDeleteInAuthor() {
        // Given: Author data
        Author author = new Author();
        author.setName("John Doe");
        author.setActivated(true);

        // When: We call logicalDelete method
        author.logicalDelete();

        // Then: We verify if the author is deactivated
        assertFalse(author.getActivated());
    }

    @Test
    @DisplayName("Test getters and setters")
    void testGettersAndSetters() {
        // Given: Author data
        Long id = 1L;
        String name = "John Doe";
        Boolean activated = true;
        List<Book> books = new ArrayList<>(); // Assuming Book is another entity

        // When: We create an instance of Author
        Author author = new Author();
        author.setId(id);
        author.setName(name);
        author.setActivated(activated);
        author.setBooks(books);

        // Then: We verify if the values were correctly assigned
        assertEquals(id, author.getId());
        assertEquals(name, author.getName());
        assertEquals(activated, author.getActivated());
        assertEquals(books, author.getBooks());
    }

    @Test
    @DisplayName("Test toString method")
    void testToString() {
        // Given: Author data
        String name = "John Doe";
        Author author = new Author();
        author.setName(name);

        // When: We call toString method
        String expectedToString = "Author(id=null, name=John Doe, activated=null, books=null)";
        String actualToString = author.toString();

        // Then: We verify if the result is as expected
        assertEquals(expectedToString, actualToString);
    }

    @Test
    @DisplayName("Test equals method")
    void testEquals() {
        // Given: Author data
        Author author1 = new Author();
        author1.setId(1L);
        Author author2 = new Author();
        author2.setId(1L);
        Author author3 = new Author();
        author3.setId(2L);

        // When: We compare authors
        // Then: We verify if authors with the same ID are equal
        assertEquals(author1, author2);
        // Then: We verify if authors with different IDs are not equal
        assertNotEquals(author1, author3);
    }

    @Test
    @DisplayName("Test hashCode method")
    void testHashCode() {
        // Given: Author data
        Author author1 = new Author();
        author1.setId(1L);
        Author author2 = new Author();
        author2.setId(1L);
        Author author3 = new Author();
        author3.setId(2L);

        // When: We get hash codes
        int hashCode1 = author1.hashCode();
        int hashCode2 = author2.hashCode();
        int hashCode3 = author3.hashCode();

        // Then: We verify if authors with the same ID have the same hash code
        assertEquals(hashCode1, hashCode2);
        // Then: We verify if authors with different IDs have different hash codes
        assertNotEquals(hashCode1, hashCode3);
    }

    @Test
    @DisplayName("Test constructor with all parameters")
    void testConstructorWithAllParameters() {
        // Given: Author data
        Long id = 1L;
        String name = "John Doe";
        Boolean activated = true;
        List<Book> books = new ArrayList<>(); // Assuming Book is another entity

        // When: We create an instance of Author using constructor with all parameters
        Author author = new Author(id, name, activated, books);

        // Then: We verify if the values were correctly assigned
        assertEquals(id, author.getId());
        assertEquals(name, author.getName());
        assertEquals(activated, author.getActivated());
        assertEquals(books, author.getBooks());
    }

    @Test
    @DisplayName("Test equals method between two Author objects")
    void testEqualsBetweenAuthors() {
        // Given: Author data
        Author author1 = new Author();
        author1.setId(1L);
        Author author2 = new Author();
        author2.setId(1L);
        Author author3 = new Author();
        author3.setId(2L);

        // Then: We verify if authors with the same ID are equal
        assertTrue(author1.equals(author2));
        // Then: We verify if authors with different IDs are not equal
        assertFalse(author1.equals(author3));
    }


}
