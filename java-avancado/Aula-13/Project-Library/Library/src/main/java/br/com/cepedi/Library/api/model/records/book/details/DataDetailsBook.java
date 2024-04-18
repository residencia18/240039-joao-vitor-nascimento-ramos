package br.com.cepedi.Library.api.model.records.book.details;

import br.com.cepedi.Library.api.model.records.author.details.DataDetailsAuthor;
import br.com.cepedi.Library.api.model.records.publisher.details.DataDetailsPublisher;
import br.com.cepedi.Library.api.model.entitys.Book;

public record DataDetailsBook(
        Long id,
        String name,
        Integer year_publication,
        DataDetailsAuthor author,
        DataDetailsPublisher publisher,
        Boolean activated
) {
    public DataDetailsBook(Book book) {
        this(book.getId(), book.getName(), book.getYearPublication(), new DataDetailsAuthor(book.getAuthor()), new DataDetailsPublisher(book.getPublisher()), book.getActivated());
    }
}