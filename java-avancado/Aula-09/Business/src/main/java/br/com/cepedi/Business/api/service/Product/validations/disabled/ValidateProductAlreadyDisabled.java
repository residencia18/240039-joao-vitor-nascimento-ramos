package br.com.cepedi.Business.api.service.Product.validations.disabled;

import br.com.cepedi.Business.api.repository.ProductRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;

public class ValidateProductAlreadyDisabled implements  ValidateProductDisabled{

    @Autowired
    private ProductRepository repository;

    @Override
    public void validation(Long id) {
        Boolean clientActivated = repository.findActivatedById(id);
        if(!clientActivated){
            throw new ValidationException("The required product already disabled");
        }
    }
}
