package br.com.cepedi.Business.api.service.sale.validations.register;

import br.com.cepedi.Business.api.model.records.sale.input.DataRegisterSale;
import br.com.cepedi.Business.api.repository.EmployeeRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidateEmployeeNotDisabledForRegisterSale implements ValidationRegisterSale{

    @Autowired
    private EmployeeRepository repository;


    @Override
    public void validation(DataRegisterSale data) {
        Boolean activated = repository.findActivatedById(data.idEmployee());
        if(!activated){
            throw  new ValidationException("The employee required is disabled");
        }
    }
}
