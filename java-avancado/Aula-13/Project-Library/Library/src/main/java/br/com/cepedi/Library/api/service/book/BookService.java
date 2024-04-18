package br.com.cepedi.Library.api.service.book;

import br.com.cepedi.Library.api.model.entitys.Author;
import br.com.cepedi.Library.api.model.entitys.Book;
import br.com.cepedi.Library.api.model.entitys.Publisher;
import br.com.cepedi.Library.api.model.records.book.input.DataRegisterBook;
import br.com.cepedi.Library.api.model.records.book.input.DataUpdateBook;
import br.com.cepedi.Library.api.model.records.book.details.DataDetailsBook;
import br.com.cepedi.Library.api.repository.AuthorRepository;
import br.com.cepedi.Library.api.repository.BookRepository;
import br.com.cepedi.Library.api.repository.PublisherRepository;
import br.com.cepedi.Library.api.service.book.validations.disabled.ValidationDisabledBook;
import br.com.cepedi.Library.api.service.book.validations.register.ValidationRegisterBook;
import br.com.cepedi.Library.api.service.book.validations.update.ValidationUpdateBook;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {


    @Autowired
    BookRepository repository;

    @Autowired
    AuthorRepository repositoryAuthor;

    @Autowired
    PublisherRepository repositoryPublisher;

    @Autowired
    List<ValidationRegisterBook> validationsRegister;

    @Autowired
    List<ValidationDisabledBook> validationsDisabled;

    @Autowired
    List<ValidationUpdateBook> validationsUpdate;

    public DataDetailsBook register(@Valid DataRegisterBook data) {

        validationsRegister.forEach(v -> v.validation(data));

        Author author = repositoryAuthor.getReferenceById(data.author_id());
        Publisher publisher = repositoryPublisher.getReferenceById(data.publisher_id());
        Book book = new Book(data.name(), data.anoPublicacao(), author, publisher);
        repository.save(book);
        return new DataDetailsBook(book);
    }

    public Page<DataDetailsBook> list(Pageable pageable) {
        return repository.findAllByActivatedTrue(pageable).map(DataDetailsBook::new);
    }

    public DataDetailsBook findById(Long id) {
        Book book = repository.getReferenceById(id);
        return new DataDetailsBook(book);

    }

    public DataDetailsBook update(DataUpdateBook data) {

        validationsUpdate.forEach(v -> v.validation(data));

        Book book = repository.getReferenceById(data.id());
        Author author = null;
        Publisher publisher = null;

        if(data.authorId() != null){
            author = repositoryAuthor.getReferenceById(data.authorId());
        }

        if(data.publisherId() != null){
            publisher = repositoryPublisher.getReferenceById(data.publisherId());
        }

        book.updateData(data.name(), data.anoPublicacao(), author , publisher);
        return new DataDetailsBook(book);
    }

    public void disabled(Long id){

        validationsDisabled.forEach(v -> v.validation(id));

        Book book = repository.getReferenceById(id);
        book.logicalDelete();
    }

}
