package br.com.cepedi.Library.api.service.loan.validations.finish;


import br.com.cepedi.Library.api.repository.LoanRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidateLoanAlreadyFinish implements ValidationFinishLoan{

    @Autowired
    private LoanRepository repository;

    @Override
    public void validation(Long id) {

        Boolean loanIsOpen = repository.findActivatedById(id);
        if(!loanIsOpen){
            throw new ValidationException("The provided loan id already finish");
        }
    }
}
