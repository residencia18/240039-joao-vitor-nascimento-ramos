package br.com.cepedi.Library.api.model.records.book.details;

import br.com.cepedi.Library.api.model.entitys.Author;
import br.com.cepedi.Library.api.model.entitys.Book;
import br.com.cepedi.Library.api.model.entitys.Publisher;
import br.com.cepedi.Library.api.model.records.author.details.DataDetailsAuthor;
import br.com.cepedi.Library.api.model.records.author.input.DataRegisterAuthor;
import br.com.cepedi.Library.api.model.records.publisher.details.DataDetailsPublisher;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("DataDetailsBook Test")
public class TestDataDetailsBook {

    @Test
    @DisplayName("DataDetailsBook Constructor Test")
    public void testDataDetailsBookConstructor() {
        Author author = new Author(new DataRegisterAuthor("John Doe"));
        Publisher publisher = new Publisher(1L, "Example Publisher", true, Collections.emptyList());
        Book book = new Book("Example Book", 2022, author, publisher);

        DataDetailsBook dataDetailsBook = new DataDetailsBook(book);

        assertEquals(book.getId(), dataDetailsBook.id());
        assertEquals(book.getName(), dataDetailsBook.name());
        assertEquals(book.getYearPublication(), dataDetailsBook.year_publication());
        assertTrue(dataDetailsBook.activated());

        DataDetailsAuthor authorDetails = new DataDetailsAuthor(author);
        assertEquals(authorDetails, dataDetailsBook.author());

        DataDetailsPublisher publisherDetails = new DataDetailsPublisher(publisher);
        assertEquals(publisherDetails, dataDetailsBook.publisher());
    }
}
