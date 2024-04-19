package br.com.cepedi.Business.api.service.role.validations.update;

import br.com.cepedi.Business.api.model.records.role.input.DataUpdateRole;
import br.com.cepedi.Business.api.repository.RoleRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidateRoleDisabled implements ValidateUpdateRole{

    @Autowired
    private RoleRepository repository;

    @Override
    public void validation(Long id, DataUpdateRole data) {
        Boolean activated = repository.findActivatedById(id);
        if(!activated){
            throw new ValidationException("The required role already disabled");
        }
    }
}
