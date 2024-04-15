package br.com.cepedi.Library.api.service.author.validations.update;

import br.com.cepedi.Library.api.model.records.author.input.DataUpdateAuthor;
import br.com.cepedi.Library.api.repository.AuthorRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidateAuthorExistence implements ValidationUpdateAuthor{

    @Autowired
    private AuthorRepository repository;

    @Override
    public void validation(DataUpdateAuthor data) {
        if(!repository.existsById(data.id())){
            throw  new ValidationException("The required author is does not exists");
        }
    }
}
