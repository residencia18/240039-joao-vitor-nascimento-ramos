package br.com.cepedi.Library.api.service.book.validations.disabled;

import br.com.cepedi.Library.api.model.records.book.input.DataRegisterBook;
import br.com.cepedi.Library.api.repository.AuthorRepository;
import br.com.cepedi.Library.api.repository.BookRepository;
import br.com.cepedi.Library.api.service.author.validations.disabled.ValidationDisabledAuthor;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidateBookAlreadyDisabled implements ValidationDisabledBook {

    @Autowired
    private BookRepository repository;

    @Override
    public void validation(Long id) {
        Boolean bookActivated = repository.findActivatedById(id);
        if(!bookActivated){
            throw new ValidationException("The required book already disabled");
        }
    }
}
