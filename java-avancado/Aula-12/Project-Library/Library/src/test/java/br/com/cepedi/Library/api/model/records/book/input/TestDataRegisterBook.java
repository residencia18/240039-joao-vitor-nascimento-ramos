package br.com.cepedi.Library.api.model.records.book.input;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Tests for DataRegisterBook class")
public class TestDataRegisterBook {

    @Test
    @DisplayName("Test constructor with valid data")
    void testConstructorWithValidData() {
        // Given: Valid book data
        String name = "Book Name";
        Integer anoPublicacao = 2022;
        Long authorId = 1L;
        Long publisherId = 1L;

        // When: We create an instance of DataRegisterBook with valid data
        DataRegisterBook dataRegisterBook = new DataRegisterBook(name, anoPublicacao, authorId, publisherId);

        // Then: We verify if the values were correctly assigned
        assertEquals(name, dataRegisterBook.name());
        assertEquals(anoPublicacao, dataRegisterBook.anoPublicacao());
        assertEquals(authorId, dataRegisterBook.author_id());
        assertEquals(publisherId, dataRegisterBook.publisher_id());
    }
}
