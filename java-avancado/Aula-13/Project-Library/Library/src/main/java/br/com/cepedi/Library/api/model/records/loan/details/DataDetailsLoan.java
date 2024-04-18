package br.com.cepedi.Library.api.model.records.loan.details;

import br.com.cepedi.Library.api.model.entitys.Loan;
import br.com.cepedi.Library.api.model.records.book.details.DataDetailsBook;
import br.com.cepedi.Library.api.model.records.client.details.DataDetailsClient;

import java.time.LocalDateTime;

public record DataDetailsLoan(
        Long id,
        DataDetailsBook book,
        DataDetailsClient client,
        LocalDateTime startDate,
        LocalDateTime endDate,
        Boolean activated
) {
    public DataDetailsLoan(Loan loan) {
        this(loan.getId(), new DataDetailsBook(loan.getBook()), new DataDetailsClient(loan.getClient()), loan.getStartDate(), loan.getEndDate(), loan.getActivated());
    }
}