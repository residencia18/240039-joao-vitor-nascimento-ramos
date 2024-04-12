package br.com.cepedi.Library.api.service.publisher.validations.update;

import br.com.cepedi.Library.api.model.records.publisher.input.DataUpdatePublisher;
import br.com.cepedi.Library.api.repository.PublisherRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidatePublisherExistence implements ValidationUpdatePublisher{

    @Autowired
    private PublisherRepository repository;

    @Override
    public void validation(DataUpdatePublisher data) {
        if(!repository.existsById(data.id())){
            throw  new ValidationException("The required publisher does not exists");
        }
    }
}
