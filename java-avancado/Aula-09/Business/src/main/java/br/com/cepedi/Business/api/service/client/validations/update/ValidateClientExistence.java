package br.com.cepedi.Business.api.service.client.validations.update;

import br.com.cepedi.Library.api.model.records.client.input.DataUpdateClient;
import br.com.cepedi.Library.api.repository.ClientRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidateClientExistence implements  ValidationUpdateClient{

    @Autowired
    private ClientRepository repository;

    @Override
    public void validation(DataUpdateClient data) {
        if(!repository.existsById(data.id())){
            throw  new ValidationException("The required client is does not exists");
        }
    }
}
