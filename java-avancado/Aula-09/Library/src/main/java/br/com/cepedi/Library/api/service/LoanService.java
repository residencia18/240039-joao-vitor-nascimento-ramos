package br.com.cepedi.Library.api.service;

import br.com.cepedi.Library.api.model.entitys.*;
import br.com.cepedi.Library.api.model.records.book.output.DataDetailsBook;
import br.com.cepedi.Library.api.model.records.loan.input.DataRegisterLoan;
import br.com.cepedi.Library.api.model.records.loan.input.DataUpdateLoan;
import br.com.cepedi.Library.api.model.records.loan.output.DataDetailsLoan;
import br.com.cepedi.Library.api.repository.BookRepository;
import br.com.cepedi.Library.api.repository.ClientRepository;
import br.com.cepedi.Library.api.repository.LoanRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class LoanService {


    @Autowired
    private LoanRepository repository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ClientRepository clientRepository;

    public DataDetailsLoan register(@Valid DataRegisterLoan data) {
        Book book = bookRepository.getReferenceById(data.book_id());
        Client client = clientRepository.getReferenceById(data.client_id());
        Loan loan = new Loan(client,book);
        repository.save(loan);
        return new DataDetailsLoan(loan);
    }


    public Page<DataDetailsLoan> list(Pageable pageable) {
        return repository.findAllByActivatedTrue(pageable).map(DataDetailsLoan::new);
    }

    public DataDetailsLoan findById(Long id) {
        Loan loan = repository.getReferenceById(id);
        return new DataDetailsLoan(loan);

    }

    public DataDetailsLoan update(DataUpdateLoan data) {
        Loan loan  = repository.getReferenceById(data.id());
        Book book = null;
        Client client = null;

        if(data.book_id() != null){
            book = bookRepository.getReferenceById(data.book_id());
        }

        if(data.client_id() != null){
            client = clientRepository.getReferenceById(data.client_id());
        }

        loan.updateData(client,book);
        return new DataDetailsLoan(loan);
    }


    public void finish(Long id){
        Loan loan = repository.getReferenceById(id);
        loan.finish();
    }

}
