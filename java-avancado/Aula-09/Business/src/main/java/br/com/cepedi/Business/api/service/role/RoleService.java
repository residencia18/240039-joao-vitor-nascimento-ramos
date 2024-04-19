package br.com.cepedi.Business.api.service.role;

import br.com.cepedi.Business.api.model.entitys.Role;
import br.com.cepedi.Business.api.model.records.role.details.DataDetailsRole;
import br.com.cepedi.Business.api.model.records.role.input.DataRegisterRole;
import br.com.cepedi.Business.api.model.records.role.input.DataUpdateRole;
import br.com.cepedi.Business.api.repository.RoleRepository;
import br.com.cepedi.Business.api.service.role.validations.disabled.ValidateDisabledRole;
import br.com.cepedi.Business.api.service.role.validations.update.ValidateRoleDisabled;
import br.com.cepedi.Business.api.service.role.validations.update.ValidateUpdateRole;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleRepository repository;

    @Autowired
    private List<ValidateUpdateRole> validationsUpdate;

    @Autowired
    private List<ValidateDisabledRole> validationsDisabled;


    public DataDetailsRole register(@Valid DataRegisterRole data) {
        Role role = new Role(data);
        repository.save(role);
        return new DataDetailsRole(role);
    }

    public Page<DataDetailsRole> list(Pageable pageable) {
        return repository.findAllByActivatedTrue(pageable).map(DataDetailsRole::new);
    }

    public DataDetailsRole findById(Long id) {
        Role role = repository.getReferenceById(id);
        return new DataDetailsRole(role);

    }

    public DataDetailsRole update(Long id , DataUpdateRole data) {
        Role role = repository.getReferenceById(id);
        role.updateData(data);
        return new DataDetailsRole(role);
    }

    public void disabled(Long id){
        Role role  = repository.getReferenceById(id);
        role.logicalDelete();
    }


}
