package br.com.cepedi.Business.api.service.client.validations.disabled;

import br.com.cepedi.Business.api.repository.ClientRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidateClientAlreadyDisabled implements ValidationDisabledClient {

    @Autowired
    private ClientRepository repository;

    @Override
    public void validation(Long id) {
        Boolean clientActivated = repository.findActivatedById(id);
        if(!clientActivated){
            throw new ValidationException("The required client already disabled");
        }
    }
}
