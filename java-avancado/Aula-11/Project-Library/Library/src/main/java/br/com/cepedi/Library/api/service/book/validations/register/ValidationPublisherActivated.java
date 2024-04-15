package br.com.cepedi.Library.api.service.book.validations.register;

import br.com.cepedi.Library.api.model.records.book.input.DataRegisterBook;
import br.com.cepedi.Library.api.repository.PublisherRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidationPublisherActivated implements ValidationRegisterBook{

    @Autowired
    private PublisherRepository repository;

    @Override
    public void validation(DataRegisterBook data) {
        Boolean publisherActivated = repository.findActivatedById(data.publisher_id());
        if(!publisherActivated){
            throw  new ValidationException("Book cannot be made with an inactive publisher");
        }
    }
}
