package br.com.cepedi.Library.api.model.records.loan.input;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("Tests for DataRegisterLoan class")
public class TestDataRegisterLoan {

    @Test
    @DisplayName("Test constructor with valid data")
    void testConstructorWithValidData() {
        // Given: Valid loan data
        Long bookId = 1L;
        Long clientId = 2L;

        // When: We create an instance of DataRegisterLoan with valid data
        DataRegisterLoan dataRegisterLoan = new DataRegisterLoan(bookId, clientId);

        // Then: We verify if the values were correctly assigned
        assertNotNull(dataRegisterLoan);
        assertEquals(bookId, dataRegisterLoan.book_id());
        assertEquals(clientId, dataRegisterLoan.client_id());
    }

}
