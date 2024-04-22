package br.com.cepedi.Business.api.service.product.validations.disabled;

import br.com.cepedi.Business.api.repository.ProductRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidateProductExistenceForDisabled implements ValidateProductDisabled{

    @Autowired
    private ProductRepository repository;

    @Override
    public void validation(Long id) {
        if(!repository.existsById(id)){
            throw  new ValidationException("The required product is does not exists");
        }
    }
}
