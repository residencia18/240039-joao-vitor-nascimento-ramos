package br.com.cepedi.Library.api.service.author.validations.disabled;

import br.com.cepedi.Library.api.repository.AuthorRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidateAuthorAlreadyDisabled  implements  ValidationDisabledAuthor {

    @Autowired
    private AuthorRepository repository;

    @Override
    public void validation(Long id) {
        Boolean authorActivated = repository.findActivatedById(id);
        if(!authorActivated){
            throw new ValidationException("The required author already disabled");
        }
    }
}
