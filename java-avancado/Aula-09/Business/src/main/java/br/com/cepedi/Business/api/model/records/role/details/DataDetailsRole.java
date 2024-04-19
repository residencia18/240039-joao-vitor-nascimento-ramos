package br.com.cepedi.Business.api.model.records.role.details;

import br.com.cepedi.Business.api.model.entitys.Role;

public record DataDetailsRole (

    Long id,
    String name
){
    public DataDetailsRole(Role role){
        this(role.getId(),role.getName());
    }

}
