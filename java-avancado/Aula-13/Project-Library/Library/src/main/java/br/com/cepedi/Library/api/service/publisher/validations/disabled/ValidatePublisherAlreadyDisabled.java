package br.com.cepedi.Library.api.service.publisher.validations.disabled;

import br.com.cepedi.Library.api.repository.PublisherRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidatePublisherAlreadyDisabled implements ValidationDisabledPublisher{

    @Autowired
    private PublisherRepository repository;


    @Override
    public void validation(Long id) {
        Boolean publisherActivated = repository.findActivatedById(id);
        if(!publisherActivated){
            throw new ValidationException("The required publisher already disabled");
        }
    }
}
