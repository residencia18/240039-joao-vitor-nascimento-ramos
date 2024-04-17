package br.com.cepedi.Library.api.service.loan.validations.finish;

import br.com.cepedi.Library.api.repository.LoanRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidateLoanExistence  implements  ValidationFinishLoan{

    @Autowired
    private LoanRepository repository;

    @Override
    public void validation(Long id) {
        if(!repository.existsById(id)){
            throw  new ValidationException("The provided loan id does not exists");
        }
    }
}
