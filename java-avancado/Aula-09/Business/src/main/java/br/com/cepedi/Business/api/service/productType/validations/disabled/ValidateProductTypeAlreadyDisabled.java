package br.com.cepedi.Business.api.service.productType.validations.disabled;

import br.com.cepedi.Business.api.repository.ProductTypeRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;

public class ValidateProductTypeAlreadyDisabled implements  ValidateDisabledProductType{

    @Autowired
    private ProductTypeRepository repository;


    @Override
    public void validation(Long id) {
        Boolean activated = repository.findActivatedById(id);
        if(!activated){
            throw new ValidationException("The required product type already disabled");
        }
    }
}
