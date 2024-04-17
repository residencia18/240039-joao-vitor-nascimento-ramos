package br.com.cepedi.Library.api.service.book.validations.register;

import br.com.cepedi.Library.api.model.records.book.input.DataRegisterBook;
import br.com.cepedi.Library.api.repository.AuthorRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidateAuthorExists implements ValidationRegisterBook{

    @Autowired
    private AuthorRepository repository;

    @Override
    public void validation(DataRegisterBook data) {
        if(!repository.existsById(data.author_id())){
            throw  new ValidationException("The required author does not exists");
        }
    }
}
