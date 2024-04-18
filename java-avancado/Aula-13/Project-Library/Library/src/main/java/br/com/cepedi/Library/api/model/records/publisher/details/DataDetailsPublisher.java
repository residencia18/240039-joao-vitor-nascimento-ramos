package br.com.cepedi.Library.api.model.records.publisher.details;

import br.com.cepedi.Library.api.model.entitys.Publisher;

public record DataDetailsPublisher(
        Long id,
        String name,
        Boolean activated
) {
    public DataDetailsPublisher(Publisher publisher) {
        this(publisher.getId(), publisher.getName(), publisher.getActivated());
    }

}
