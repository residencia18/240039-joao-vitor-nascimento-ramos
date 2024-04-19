package br.com.cepedi.Business.api.service.productType.validations.update;

import br.com.cepedi.Business.api.model.records.productType.input.DataRegisterProductType;
import br.com.cepedi.Business.api.model.records.productType.input.DataUpdateProductType;
import br.com.cepedi.Business.api.repository.ProductTypeRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;

public class ValidateProductTypeDisabled implements ValidateUpdateProductType{

    @Autowired
    private ProductTypeRepository repository;

    @Override
    public void validation(Long id , DataUpdateProductType data) {
        Boolean activated = repository.findActivatedById(id);
        if(!activated){
            throw new ValidationException("The required product type already disabled");
        }
    }
}
