package br.com.cepedi.Library.api.model.records.publisher.details;

import br.com.cepedi.Library.api.model.entitys.Publisher;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("DataDetailsPublisher Test")
public class TestDataDetailsPublisher {

    @Test
    @DisplayName("DataDetailsPublisher Constructor Test")
    public void testDataDetailsPublisherConstructor() {
        Publisher publisher = new Publisher(1L, "Example Publisher", true, Collections.emptyList());
        DataDetailsPublisher dataDetailsPublisher = new DataDetailsPublisher(publisher);

        assertEquals(1L, dataDetailsPublisher.id());
        assertEquals("Example Publisher", dataDetailsPublisher.name());
        assertTrue(dataDetailsPublisher.activated());
    }


}