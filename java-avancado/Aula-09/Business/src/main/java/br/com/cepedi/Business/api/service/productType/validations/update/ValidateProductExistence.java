package br.com.cepedi.Business.api.service.productType.validations.update;

import br.com.cepedi.Business.api.model.records.productType.input.DataUpdateProductType;
import br.com.cepedi.Business.api.repository.ProductTypeRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;

public class ValidateProductExistence implements ValidateUpdateProductType{

    @Autowired
    private ProductTypeRepository repository;

    @Override
    public void validation(Long id, DataUpdateProductType data) {
        if(!repository.existsById(id)){
            throw  new ValidationException("The required product is does not exists");
        }
    }
}
