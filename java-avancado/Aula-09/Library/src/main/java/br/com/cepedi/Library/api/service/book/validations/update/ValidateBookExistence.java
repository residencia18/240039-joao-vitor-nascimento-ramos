package br.com.cepedi.Library.api.service.book.validations.update;

import br.com.cepedi.Library.api.model.records.book.input.DataUpdateBook;
import br.com.cepedi.Library.api.repository.BookRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidateBookExistence implements ValidationUpdateBook {

    @Autowired
    private BookRepository repository;

    @Override
    public void validation(DataUpdateBook data) {
        if(!repository.existsById(data.id())){
            throw new ValidationException("The required book does not exists");
        }
    }
}
