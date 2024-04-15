package br.com.cepedi.Library.api.service.author;

import br.com.cepedi.Library.api.model.entitys.Author;
import br.com.cepedi.Library.api.model.entitys.Client;
import br.com.cepedi.Library.api.model.records.author.input.DataRegisterAuthor;
import br.com.cepedi.Library.api.model.records.author.input.DataUpdateAuthor;
import br.com.cepedi.Library.api.model.records.author.output.DataDetailsAuthor;
import br.com.cepedi.Library.api.model.records.client.input.DataRegisterClient;
import br.com.cepedi.Library.api.model.records.client.input.DataUpdateClient;
import br.com.cepedi.Library.api.model.records.client.output.DataDetailsClient;
import br.com.cepedi.Library.api.model.records.publisher.input.DataRegisterPublisher;
import br.com.cepedi.Library.api.model.records.publisher.output.DataDetailsPublisher;
import br.com.cepedi.Library.api.repository.AuthorRepository;
import br.com.cepedi.Library.api.service.author.validations.disabled.ValidateAuthorAlreadyDisabled;
import br.com.cepedi.Library.api.service.author.validations.disabled.ValidationDisabledAuthor;
import br.com.cepedi.Library.api.service.author.validations.update.ValidationUpdateAuthor;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Service
public class AuthorService {

    @Autowired
    AuthorRepository repository;

    @Autowired
    private List<ValidationDisabledAuthor> validationsDisabled;


    @Autowired
    private List<ValidationUpdateAuthor> validationsUpdate;


    public DataDetailsAuthor register(@Valid DataRegisterAuthor data) {
        Author author = new Author(data);
        repository.save(author);
        return new DataDetailsAuthor(author);
    }

    public Page<DataDetailsAuthor> list(Pageable pageable) {
        return repository.findAllByActivatedTrue(pageable).map(DataDetailsAuthor::new);
    }

    public DataDetailsAuthor findById(Long id) {
        Author author= repository.getReferenceById(id);
        return new DataDetailsAuthor(author);
    }

    public DataDetailsAuthor update(DataUpdateAuthor data) {
        validationsUpdate.forEach(v -> v.validation(data));
        Author author = repository.getReferenceById(data.id());
        author.updateData(data);
        return new DataDetailsAuthor(author);
    }

    public void disabled(Long id){
        validationsDisabled.forEach(v -> v.validation(id));
        Author author= repository.getReferenceById(id);
        author.logicalDelete();
    }

}
