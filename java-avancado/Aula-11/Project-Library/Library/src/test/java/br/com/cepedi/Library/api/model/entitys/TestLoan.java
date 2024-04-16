package br.com.cepedi.Library.api.model.entitys;

import br.com.cepedi.Library.api.model.records.address.input.DataRegisterAddress;
import br.com.cepedi.Library.api.model.records.book.input.DataRegisterBook;
import br.com.cepedi.Library.api.model.records.client.input.DataRegisterClient;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class TestLoan {

    @Test
    @DisplayName("Test constructor with valid data")
    void testConstructorWithValidData() {
        // Given: Valid loan data
        Book book = new Book("Book Name", 2022, null, null);
        DataRegisterClient data = new DataRegisterClient("John Doe", "john.doe@example.com",
                "123456789", "123.456.789-00", new DataRegisterAddress(
                "Street", "bairro", "12345678", "city", "BA", "complement","123"));

        // When: Create Client using constructor with DataRegisterClient
        Client client = new Client(data);
        // When: We create an instance of Loan with valid data
        Loan loan = new Loan(client, book);

        // Then: We verify if the values were correctly assigned
        assertNotNull(loan);
        assertEquals(book, loan.getBook());
        assertEquals(client, loan.getClient());
        assertNotNull(loan.getStartDate());
        assertTrue(loan.getActivated());
        assertEquals(null, loan.getEndDate());
    }

    @Test
    @DisplayName("Test updateData method")
    void testUpdateData() {
        // Given: Original loan data
        Book originalBook = new Book("Original Book", 2022, null, null);
        DataRegisterClient dataOriginal = new DataRegisterClient("John Doe", "john.doe@example.com",
                "123456789", "123.456.789-00", new DataRegisterAddress(
                "Street", "bairro", "12345678", "city", "BA", "complement","123"));

        // When: Create Client using constructor with DataRegisterClient
        Client originalClient = new Client(dataOriginal);
        Loan loan = new Loan(originalClient, originalBook);

        // Given: Updated loan data
        Book updatedBook = new Book("Updated Book", 2023, null, null);
        DataRegisterClient dataUpdate = new DataRegisterClient("John Doe", "john.doe@example.com",
                "123456789", "123.456.789-00", new DataRegisterAddress(
                "Street", "bairro", "12345678", "city", "BA", "complement","123"));

        // When: Create Client using constructor with DataRegisterClient
        Client updateClient = new Client(dataUpdate);
        // When: We update the loan data
        loan.updateData(updateClient, updatedBook);

        // Then: We verify if the data was updated correctly
        assertEquals(updatedBook, loan.getBook());
        assertEquals(updateClient, loan.getClient());
    }

    @Test
    @DisplayName("Test finish method")
    void testFinish() {
        // Given: Loan data
        Book book = new Book("Book Name", 2022, null, null);
        DataRegisterClient data = new DataRegisterClient("John Doe", "john.doe@example.com",
                "123456789", "123.456.789-00", new DataRegisterAddress(
                "Street", "bairro", "12345678", "city", "BA", "complement","123"));

        // When: Create Client using constructor with DataRegisterClient
        Client client = new Client(data);
        Loan loan = new Loan(client, book);

        // When: We finish the loan
        loan.finish();

        // Then: We verify if the end date is set and if the loan is deactivated
        assertNotNull(loan.getEndDate());
        assertTrue(!loan.getActivated());
    }

    @Test
    @DisplayName("Test setters")
    void testSetters() {
        // Given: Valid loan data
        Book book = new Book("Book Name", 2022, null, null);
        DataRegisterClient data = new DataRegisterClient("John Doe", "john.doe@example.com",
                "123456789", "123.456.789-00", new DataRegisterAddress(
                "Street", "bairro", "12345678", "city", "BA", "complement","123"));

        // When: Create Client using constructor with DataRegisterClient
        Client client = new Client(data);
        Loan loan = new Loan(client, book);

        // Given: New book and client
        Book newBook = new Book("New Book", 2023, null, null);
        Client newClient =  new Client(data);

        // When: We set new values using setters
        loan.setBook(newBook);
        loan.setClient(newClient);
        loan.setStartDate(LocalDateTime.now().minusDays(1));
        loan.setEndDate(LocalDateTime.now());
        loan.setActivated(false);

        // Then: We verify if the values were set correctly
        assertEquals(newBook, loan.getBook());
        assertEquals(newClient, loan.getClient());
        assertNotNull(loan.getStartDate());
        assertNotNull(loan.getEndDate());
        assertTrue(!loan.getActivated());
    }

    @Test
    @DisplayName("Test hashCode method")
    void testHashCode() {
        // Given: Client and Book data
        DataRegisterClient clientData = new DataRegisterClient("John Doe", "john.doe@example.com",
                "123456789", "123.456.789-00", new DataRegisterAddress(
                "Street", "bairro", "12345678", "city", "BA", "complement","123"));
        Client client = new Client(clientData);

        DataRegisterBook bookData = new DataRegisterBook("Book Name", 2022, 1L, 1L);
        Book book = new Book(bookData.name(), bookData.anoPublicacao(), null, null);

        // When: Create Loan
        Loan loan1 = new Loan(client, book);
        Loan loan2 = new Loan(client, book);
        loan1.setId(1L);
        loan2.setId(1L);

        // Then: We verify if hashCode method works correctly
        assertEquals(loan1.hashCode(), loan2.hashCode());
    }

    @Test
    @DisplayName("Test equals method")
    void testEquals() {
        // Given: Client and Book data
        DataRegisterClient clientData = new DataRegisterClient("John Doe", "john.doe@example.com",
                "123456789", "123.456.789-00", new DataRegisterAddress(
                "Street", "bairro", "12345678", "city", "BA", "complement","123"));
        Client client = new Client(clientData);

        DataRegisterBook bookData = new DataRegisterBook("Book Name", 2022, 1L, 1L);
        Book book = new Book(bookData.name(), bookData.anoPublicacao(), null, null);

        // When: Create Loan
        Loan loan1 = new Loan(client, book);
        Loan loan2 = new Loan(client, book);
        loan1.setId(1L);
        loan2.setId(1L);

        // Then: We verify if equals method works correctly
        assertTrue(loan1.equals(loan2));
    }

}
