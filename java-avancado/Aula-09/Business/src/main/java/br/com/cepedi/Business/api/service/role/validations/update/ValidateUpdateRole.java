package br.com.cepedi.Business.api.service.role.validations.update;

import br.com.cepedi.Business.api.model.records.role.input.DataUpdateRole;

public interface ValidateUpdateRole {

    void validation(Long id, DataUpdateRole data);

}
