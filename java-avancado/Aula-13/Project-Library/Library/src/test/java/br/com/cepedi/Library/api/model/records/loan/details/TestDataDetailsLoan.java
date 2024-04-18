package br.com.cepedi.Library.api.model.records.loan.details;

import br.com.cepedi.Library.api.model.entitys.*;
import br.com.cepedi.Library.api.model.records.address.input.DataRegisterAddress;
import br.com.cepedi.Library.api.model.records.author.input.DataRegisterAuthor;
import br.com.cepedi.Library.api.model.records.book.details.DataDetailsBook;
import br.com.cepedi.Library.api.model.records.client.details.DataDetailsClient;
import br.com.cepedi.Library.api.model.records.client.input.DataRegisterClient;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestDataDetailsLoan {

    @Test
    @DisplayName("Test Details Loan")
    void testDataDetailsLoanConstructor() {

        Author author = new Author(new DataRegisterAuthor("John Doe"));
        Publisher publisher = new Publisher(1L, "Example Publisher", true, Collections.emptyList());
        Book book = new Book("Example Book", 2022, author, publisher);
        DataDetailsBook dataDetailsBook = new DataDetailsBook(book);


        DataRegisterAddress address = new DataRegisterAddress("Main Street", "Downtown", "12345-678", "City", "UF", "Apartment 101", "123");
        Client client = new Client(new DataRegisterClient(
                "John Doe", "john@example.com", "123456789", "123.456.789-10", address));
        DataDetailsClient dataDetailsClient = new DataDetailsClient(client);


        Loan loan = new Loan();
        loan.setId(1L);
        loan.setBook(book);
        loan.setClient(client);
        loan.setStartDate(LocalDateTime.of(2024, 4, 17, 12, 0));
        loan.setEndDate(LocalDateTime.of(2024, 4, 24, 12, 0));
        loan.setActivated(true);

        // Criar instância de DataDetailsLoan usando o construtor
        DataDetailsLoan dataDetailsLoan = new DataDetailsLoan(loan);

        // Verificar se os valores foram atribuídos corretamente
        assertEquals(1L, dataDetailsLoan.id());
        assertEquals(LocalDateTime.of(2024, 4, 17, 12, 0), dataDetailsLoan.startDate());
        assertEquals(LocalDateTime.of(2024, 4, 24, 12, 0), dataDetailsLoan.endDate());
        assertEquals(true, dataDetailsLoan.activated());

        // Verificar se os objetos DataDetailsBook e DataDetailsClient foram criados corretamente
        assertEquals(dataDetailsBook, dataDetailsLoan.book());
        assertEquals(dataDetailsClient, dataDetailsLoan.client());
    }
}


