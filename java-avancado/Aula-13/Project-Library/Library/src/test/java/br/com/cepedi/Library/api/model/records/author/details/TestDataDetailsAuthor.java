package br.com.cepedi.Library.api.model.records.author.details;

import br.com.cepedi.Library.api.model.entitys.Author;
import br.com.cepedi.Library.api.model.records.author.input.DataRegisterAuthor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Test DataDetailsAuthor")
public class TestDataDetailsAuthor {

    @Test
    @DisplayName("DataDetailsAuthor Constructor Test")
    public void testDataDetailsAuthorConstructor() {
        Author author = new Author(new DataRegisterAuthor("John Doe"));
        author.setId(1L);
        DataDetailsAuthor dataDetailsAuthor = new DataDetailsAuthor(author);

        assertEquals(1L, dataDetailsAuthor.id());
        assertEquals("John Doe", dataDetailsAuthor.name());
        assertTrue(dataDetailsAuthor.activated());
    }

    @Test
    @DisplayName("Test name author")
    public void testDataDetailsAuthorName() {
        Author author = new Author(new DataRegisterAuthor("John Doe"));
        DataDetailsAuthor dataDetailsAuthor = new DataDetailsAuthor(author);

        assertEquals("John Doe", dataDetailsAuthor.name());
    }


}
