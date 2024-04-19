package br.com.cepedi.Business.api.service.Product.validations.update;

import br.com.cepedi.Business.api.model.records.product.input.DataUpdateProduct;
import br.com.cepedi.Business.api.repository.ProductRepository;
import br.com.cepedi.Business.api.repository.ProductTypeRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidateProductDisabled implements ValidateProductUpdate {

    @Autowired
    private ProductRepository repository;

    @Override
    public void validation(Long id ,DataUpdateProduct data) {
        Boolean clientActivated = repository.findActivatedById(id);
        if(!clientActivated){
            throw new ValidationException("The required product is disabled");
        }
    }
}
