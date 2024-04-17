package br.com.cepedi.Library.api.model.records.loan.input;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("Tests for DataUpdateLoan class")
public class TestDataUpdateLoan {

    @Test
    @DisplayName("Test constructor with valid data")
    void testConstructorWithValidData() {
        // Given: Valid loan data
        Long id = 1L;
        Long bookId = 2L;
        Long clientId = 3L;

        // When: We create an instance of DataUpdateLoan with valid data
        DataUpdateLoan dataUpdateLoan = new DataUpdateLoan(id, bookId, clientId);

        // Then: We verify if the values were correctly assigned
        assertNotNull(dataUpdateLoan);
        assertEquals(id, dataUpdateLoan.id());
        assertEquals(bookId, dataUpdateLoan.book_id());
        assertEquals(clientId, dataUpdateLoan.client_id());
    }
}
