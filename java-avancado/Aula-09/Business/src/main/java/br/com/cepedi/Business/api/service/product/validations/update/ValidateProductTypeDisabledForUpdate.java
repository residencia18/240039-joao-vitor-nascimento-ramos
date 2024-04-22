package br.com.cepedi.Business.api.service.product.validations.update;

import br.com.cepedi.Business.api.model.records.product.input.DataUpdateProduct;
import br.com.cepedi.Business.api.repository.ProductTypeRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidateProductTypeDisabledForUpdate implements ValidateProductUpdate{


    @Autowired
    private ProductTypeRepository repository;

    @Override
    public void validation(Long id ,DataUpdateProduct data) {
        if(data.idProductType() != null){
            Boolean activated = repository.findActivatedById(data.idProductType());
            if(!activated){
                throw new ValidationException("The required type product is disabled");
            }
        }
    }
}
