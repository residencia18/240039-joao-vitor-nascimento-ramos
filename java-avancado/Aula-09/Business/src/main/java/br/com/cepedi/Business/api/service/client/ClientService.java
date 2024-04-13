package br.com.cepedi.Business.api.service.client;


import br.com.cepedi.Business.api.model.entitys.Client;
import br.com.cepedi.Business.api.model.records.client.input.DataRegisterClient;
import br.com.cepedi.Business.api.model.records.client.input.DataUpdateClient;
import br.com.cepedi.Business.api.model.records.client.output.DataDetailsClient;
import br.com.cepedi.Business.api.repository.ClientRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    public DataDetailsClient register(@Valid DataRegisterClient data, UriComponentsBuilder uriBuilder) {
        Client client = new Client(data);
        repository.save(client);
        return new DataDetailsClient(client);
    }

    public Page<DataDetailsClient> list(Pageable pageable) {
        return repository.findAllByActivatedTrue(pageable).map(DataDetailsClient::new);
    }

    public DataDetailsClient findById(Long id) {
        Client client = repository.getReferenceById(id);
        return new DataDetailsClient(client);

    }

    public DataDetailsClient update(DataUpdateClient data) {
        Client client = repository.getReferenceById(data.id());
        client.updateData(data);
        return new DataDetailsClient(client);
    }

    public void disabled(Long id){
        Client client = repository.getReferenceById(id);
        client.logicalDelete();
    }
}