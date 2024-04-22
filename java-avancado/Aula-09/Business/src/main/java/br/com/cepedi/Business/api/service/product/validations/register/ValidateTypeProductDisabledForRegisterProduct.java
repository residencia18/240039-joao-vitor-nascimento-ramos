package br.com.cepedi.Business.api.service.product.validations.register;

import br.com.cepedi.Business.api.model.records.product.input.DataRegisterProduct;
import br.com.cepedi.Business.api.repository.ProductTypeRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidateTypeProductDisabledForRegisterProduct implements ValidateProductRegister{

    @Autowired
    private ProductTypeRepository repository;

    @Override
    public void validation(DataRegisterProduct data) {
        Boolean activated = repository.findActivatedById(data.idProductType());
        if(!activated){
            throw new ValidationException("The required type product is disabled");
        }
    }
}
