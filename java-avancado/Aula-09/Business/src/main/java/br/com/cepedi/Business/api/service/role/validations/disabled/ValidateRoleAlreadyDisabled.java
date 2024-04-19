package br.com.cepedi.Business.api.service.role.validations.disabled;

import br.com.cepedi.Business.api.repository.RoleRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidateRoleAlreadyDisabled implements ValidateDisabledRole{

    @Autowired
    private RoleRepository repository;

    @Override
    public void validation(Long id) {
        Boolean activated = repository.findActivatedById(id);
        if(!activated){
            throw new ValidationException("The required role already disabled");
        }
    }
}
