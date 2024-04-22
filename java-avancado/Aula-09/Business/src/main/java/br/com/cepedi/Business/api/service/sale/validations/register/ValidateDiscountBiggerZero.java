package br.com.cepedi.Business.api.service.sale.validations.register;

import br.com.cepedi.Business.api.model.records.sale.input.DataRegisterSale;
import jakarta.validation.ValidationException;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ValidateDiscountBiggerZero  implements ValidationRegisterSale{


    @Override
    public void validation(DataRegisterSale data) {
        if(data.discount() != null && data.discount().compareTo(BigDecimal.ZERO) <= 0){
            throw new ValidationException("discount if applied, must be greater than zero ");
        }
    }
}
