package br.com.cepedi.Library.api.model.entitys;

import br.com.cepedi.Library.api.model.records.author.input.DataRegisterAuthor;
import br.com.cepedi.Library.api.model.records.publisher.input.DataRegisterPublisher;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests for Book class")
public class TestBook {

    @Test
    @DisplayName("Test constructor with valid data")
    void testConstructorWithValidData() {
        // Given: Valid book data
        String name = "Book Name";
        Integer anoPublicacao = 2022;
        DataRegisterAuthor dataRegisterAuthor = new DataRegisterAuthor("Author name");
        Author author = new Author(dataRegisterAuthor);

        DataRegisterPublisher dataRegisterPublisher = new DataRegisterPublisher("Publisher name");
        Publisher publisher = new Publisher(dataRegisterPublisher);

        // When: We create an instance of Book with valid data
        Book book = new Book(name, anoPublicacao, author, publisher);

        // Then: We verify if the values were correctly assigned
        assertEquals(name, book.getName());
        assertEquals(anoPublicacao, book.getYearPublication());
        assertEquals(author, book.getAuthor());
        assertEquals(publisher, book.getPublisher());
        assertTrue(book.getActivated());
    }

    @Test
    @DisplayName("Test logicalDelete method")
    void testLogicalDelete() {
        // Given: A book
        String name = "Book Name";
        Integer anoPublicacao = 2022;
        DataRegisterAuthor dataRegisterAuthor = new DataRegisterAuthor("Author name");
        Author author = new Author(dataRegisterAuthor);

        DataRegisterPublisher dataRegisterPublisher = new DataRegisterPublisher("Publisher name");
        Publisher publisher = new Publisher(dataRegisterPublisher);

        // When: We create an instance of Book with valid data
        Book book = new Book(name, anoPublicacao, author, publisher);

        // When: We perform a logical delete
        book.logicalDelete();

        // Then: We verify if the book is deactivated
        assertTrue(!book.getActivated());
    }

    @Test
    @DisplayName("Test updateData method")
    void testUpdateData() {
        // Given: Original book data
        // Given: A book
        String name = "Book Name";
        Integer anoPublicacao = 2022;
        DataRegisterAuthor dataRegisterAuthor = new DataRegisterAuthor("Author name");
        Author author = new Author(dataRegisterAuthor);

        DataRegisterPublisher dataRegisterPublisher = new DataRegisterPublisher("Publisher name");
        Publisher publisher = new Publisher(dataRegisterPublisher);

        // When: We create an instance of Book with valid data
        Book book = new Book(name, anoPublicacao, author, publisher);

        // Given: Updated book data
        String updatedName = "Updated Book Name";
        Integer updatedAnoPublicacao = 2023;


        DataRegisterAuthor dataUpdateAuthor = new DataRegisterAuthor("Updated Author Name");
        Author updatedAuthor = new Author(dataUpdateAuthor);

        DataRegisterPublisher dataUpdatePublisher = new DataRegisterPublisher("Updated Publisher Name");
        Publisher updatedPublisher = new Publisher(dataUpdatePublisher);

        // When: We update the book data
        book.updateData(updatedName, updatedAnoPublicacao, updatedAuthor, updatedPublisher);

        // Then: We verify if the data was updated correctly
        assertEquals(updatedName, book.getName());
        assertEquals(updatedAnoPublicacao, book.getYearPublication());
        assertEquals(updatedAuthor, book.getAuthor());
        assertEquals(updatedPublisher, book.getPublisher());
    }

    @Test
    @DisplayName("Test equals method")
    void testEquals() {
        // Given: Two instances of Book with the same ID
        Book book1 = new Book("Book Name", 2022, new Author(new DataRegisterAuthor("Author Name")), new Publisher(new DataRegisterPublisher("Publisher Name")));
        Book book2 = new Book("Book Name", 2022, new Author(new DataRegisterAuthor("Author Name")), new Publisher(new DataRegisterPublisher("Publisher Name")));
        book1.setId(1L);
        book2.setId(1L);
        // Given: Another instance of Book with a different ID
        Book book3 = new Book( "Another Book Name", 2023, new Author(new DataRegisterAuthor("Author Name")), new Publisher(new DataRegisterPublisher("Publisher Name")));
        book3.setId(2L);
        // Then: We verify if equals method works correctly
        assertTrue(book1.equals(book2)); // Two books with the same ID should be equal
        assertFalse(book1.equals(book3)); // Two books with different IDs should not be equal
    }

    @Test
    @DisplayName("Test hashCode method")
    void testHashCode() {
        // Given: Two instances of Book with the same ID
        Book book1 = new Book("Book Name", 2022, new Author(new DataRegisterAuthor("Author Name")), new Publisher(new DataRegisterPublisher("Publisher Name")));
        Book book2 = new Book("Book Name", 2022, new Author(new DataRegisterAuthor("Author Name")), new Publisher(new DataRegisterPublisher("Publisher Name")));
        book2.setId(1L);
        book1.setId(1L);
        // Then: We verify if hashCode method works correctly
        assertEquals(book1.hashCode(), book2.hashCode()); // Two books with the same ID should have the same hashCode
    }


    @Test
    @DisplayName("Test setters")
    void testSetters() {
        // Given: Book data
        String name = "Book Name";
        Integer anoPublicacao = 2022;
        DataRegisterAuthor dataRegisterAuthor = new DataRegisterAuthor("Author name");
        Author author = new Author(dataRegisterAuthor);

        DataRegisterPublisher dataRegisterPublisher = new DataRegisterPublisher("Publisher name");
        Publisher publisher = new Publisher(dataRegisterPublisher);

        // When: We create an instance of Book with valid data
        Book book = new Book(name, anoPublicacao, author, publisher);

        // When: We set new values using the setters
        String updatedName = "Updated Book Name";
        Integer updatedAnoPublicacao = 2023;
        DataRegisterAuthor dataUpdateAuthor = new DataRegisterAuthor("Updated Author Name");
        Author updatedAuthor = new Author(dataUpdateAuthor);
        DataRegisterPublisher dataUpdatePublisher = new DataRegisterPublisher("Updated Publisher Name");
        Publisher updatedPublisher = new Publisher(dataUpdatePublisher);

        book.setName(updatedName);
        book.setYearPublication(updatedAnoPublicacao);
        book.setAuthor(updatedAuthor);
        book.setPublisher(updatedPublisher);

        // Then: We verify if the values were correctly updated
        assertEquals(updatedName, book.getName());
        assertEquals(updatedAnoPublicacao, book.getYearPublication());
        assertEquals(updatedAuthor, book.getAuthor());
        assertEquals(updatedPublisher, book.getPublisher());
    }



}