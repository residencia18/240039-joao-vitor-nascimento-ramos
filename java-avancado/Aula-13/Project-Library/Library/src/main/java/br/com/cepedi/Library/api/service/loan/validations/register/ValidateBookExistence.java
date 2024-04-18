package br.com.cepedi.Library.api.service.loan.validations.register;

import br.com.cepedi.Library.api.model.records.loan.input.DataRegisterLoan;
import br.com.cepedi.Library.api.repository.BookRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidateBookExistence implements  ValidationRegisterLoan{

    @Autowired
    private BookRepository repository;

    public void validation(DataRegisterLoan data){
        if (repository.existsById(data.book_id())) {
            throw new ValidationException("The provided book id does not exist");
        }
    }



}
