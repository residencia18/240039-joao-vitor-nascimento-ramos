package br.com.cepedi.Library.api.service;

import br.com.cepedi.Library.api.model.entitys.Publisher;
import br.com.cepedi.Library.api.model.records.publisher.input.DataRegisterPublisher;
import br.com.cepedi.Library.api.model.records.publisher.input.DataUpdatePublisher;
import br.com.cepedi.Library.api.model.records.publisher.output.DataDetailsPublisher;
import br.com.cepedi.Library.api.repository.PublisherRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PublisherService {

    @Autowired
    PublisherRepository repository;

    public DataDetailsPublisher register(@Valid DataRegisterPublisher data) {
        Publisher publisher = new Publisher(data);
        repository.save(publisher);
        return new DataDetailsPublisher(publisher);
    }

    public Page<DataDetailsPublisher> list(Pageable pageable) {
        return repository.findAllByActivatedTrue(pageable).map(DataDetailsPublisher::new);
    }

    public DataDetailsPublisher findById(Long id) {
        Publisher publisher =  repository.getReferenceById(id);
        return new DataDetailsPublisher(publisher);
    }

    public DataDetailsPublisher update(DataUpdatePublisher data) {
        Publisher publisher =  repository.getReferenceById(data.id());
        publisher.updateData(data);
        return new DataDetailsPublisher(publisher);
    }

    public void disabled(Long id){
        Publisher publisher =  repository.getReferenceById(id);
        publisher.logicalDelete();
    }

}
