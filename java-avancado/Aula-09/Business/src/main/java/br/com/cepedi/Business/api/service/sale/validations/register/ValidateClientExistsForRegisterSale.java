package br.com.cepedi.Business.api.service.sale.validations.register;

import br.com.cepedi.Business.api.model.records.sale.input.DataRegisterSale;
import br.com.cepedi.Business.api.repository.ClientRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidateClientExistsForRegisterSale  implements ValidationRegisterSale{


    @Autowired
    private ClientRepository repository;

    @Override
    public void validation(DataRegisterSale data) {
        if(!repository.existsById(data.idClient())){
            throw new ValidationException("Client required not exists");
        }
    }
}
