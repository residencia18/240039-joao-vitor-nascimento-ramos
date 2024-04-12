package br.com.cepedi.Library.api.service.loan.validations.register;

import br.com.cepedi.Library.api.model.records.loan.input.DataRegisterLoan;
import br.com.cepedi.Library.api.repository.ClientRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidationClientActivated  implements  ValidationRegisterLoan{

    @Autowired
    private ClientRepository repository;

    @Override
    public void validation(DataRegisterLoan data) {
        Boolean patientActivated = repository.findActivatedById(data.client_id());
        if(patientActivated){
            throw new ValidationException("Loan cannot be made with an inactive client");
        }
    }
}
