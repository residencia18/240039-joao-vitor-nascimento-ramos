package br.com.cepedi.Library.api.service.client.validations.disabled;

import br.com.cepedi.Library.api.repository.AuthorRepository;
import br.com.cepedi.Library.api.repository.ClientRepository;
import br.com.cepedi.Library.api.service.author.validations.disabled.ValidationDisabledAuthor;
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
            throw new ValidationException("The requered client already disabled");
        }
    }
}
