package br.com.cepedi.Business.api.service.role.validations.disabled;

import br.com.cepedi.Business.api.repository.RoleRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidateRoleExistenceForDisabled implements ValidateDisabledRole{

    @Autowired
    private RoleRepository repository;

    @Override
    public void validation(Long id) {
        if(!repository.existsById(id)){
            throw  new ValidationException("The required role is does not exists");
        }
    }
}
