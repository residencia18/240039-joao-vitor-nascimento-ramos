package br.com.cepedi.Business.api.service.client.validations.update;


import br.com.cepedi.Business.api.model.records.client.input.DataUpdateClient;

public interface ValidationUpdateClient {

    void validation(Long id , DataUpdateClient data);

}
