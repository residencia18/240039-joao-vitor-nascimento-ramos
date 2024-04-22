package br.com.cepedi.Business.api.service.sale.validations.register;

import br.com.cepedi.Business.api.model.records.sale.input.DataRegisterSale;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidateListOfProductsNotEmpty implements ValidationRegisterSale{



    @Override
    public void validation(DataRegisterSale data) {
        if(data.products().isEmpty()){
            throw  new ValidationException("List of Products is empty");
        }
    }
}
