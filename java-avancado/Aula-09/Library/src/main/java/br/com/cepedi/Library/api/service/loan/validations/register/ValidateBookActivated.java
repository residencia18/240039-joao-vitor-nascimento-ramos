package br.com.cepedi.Library.api.service.loan.validations.register;

import br.com.cepedi.Library.api.model.records.loan.input.DataRegisterLoan;
import br.com.cepedi.Library.api.repository.BookRepository;
import jakarta.validation.ValidationException;
import jdk.jfr.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidateBookActivated implements  ValidationRegisterLoan{

    @Autowired
    private BookRepository repository;

    @Override
    public void validation(DataRegisterLoan data) {
        Boolean patientActivated = repository.findActivatedById(data.book_id());
        if(patientActivated){
            throw new ValidationException("Loan cannot be made with an inactive book");
        }
    }
}
