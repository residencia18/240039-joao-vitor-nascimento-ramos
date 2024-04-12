package br.com.cepedi.Library.api.service.book.validations.register;

import br.com.cepedi.Library.api.model.records.book.input.DataRegisterBook;
import br.com.cepedi.Library.api.repository.PublisherRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidatePublisherExists implements ValidationRegisterBook{

    @Autowired
    private PublisherRepository repository;

    @Override
    public void validation(DataRegisterBook data) {
        if(!repository.existsById(data.publisher_id())){
            throw new ValidationException("The required publisher does not exists");
        }
    }
}
