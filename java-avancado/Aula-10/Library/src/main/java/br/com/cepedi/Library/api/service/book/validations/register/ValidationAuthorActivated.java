package br.com.cepedi.Library.api.service.book.validations.register;

import br.com.cepedi.Library.api.model.records.book.input.DataRegisterBook;
import br.com.cepedi.Library.api.repository.AuthorRepository;
import br.com.cepedi.Library.api.repository.BookRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidationAuthorActivated implements ValidationRegisterBook{

    @Autowired
    private AuthorRepository repository;

    @Override
    public void validation(DataRegisterBook data) {
        Boolean authorActivated = repository.findActivatedById(data.author_id());
        if(!authorActivated){
            throw  new ValidationException("Book cannot be made with an inactive author");
        }
    }
}
