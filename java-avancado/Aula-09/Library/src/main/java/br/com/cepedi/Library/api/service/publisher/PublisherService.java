package br.com.cepedi.Library.api.service.publisher;

import br.com.cepedi.Library.api.model.entitys.Publisher;
import br.com.cepedi.Library.api.model.records.publisher.input.DataRegisterPublisher;
import br.com.cepedi.Library.api.model.records.publisher.input.DataUpdatePublisher;
import br.com.cepedi.Library.api.model.records.publisher.output.DataDetailsPublisher;
import br.com.cepedi.Library.api.repository.PublisherRepository;
import br.com.cepedi.Library.api.service.publisher.validations.disabled.ValidationDisabledPublisher;
import br.com.cepedi.Library.api.service.publisher.validations.register.ValidationRegisterPublisher;
import br.com.cepedi.Library.api.service.publisher.validations.update.ValidationUpdatePublisher;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublisherService {

    @Autowired
    PublisherRepository repository;

    @Autowired
    List<ValidationRegisterPublisher> validationsRegister;

    @Autowired
    List<ValidationUpdatePublisher> validationsUpdate;

    @Autowired
    List<ValidationDisabledPublisher> validationsDisabled;

    public DataDetailsPublisher register(@Valid DataRegisterPublisher data) {
        validationsRegister.forEach(v -> v.validation(data));
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
        validationsUpdate.forEach(v -> v.validation(data));
        Publisher publisher =  repository.getReferenceById(data.id());
        publisher.updateData(data);
        return new DataDetailsPublisher(publisher);
    }

    public void disabled(Long id){
        validationsDisabled.forEach(v -> v.validation(id));
        Publisher publisher =  repository.getReferenceById(id);
        publisher.logicalDelete();
    }

}
