package br.com.cepedi.Library.api.repository;

import br.com.cepedi.Library.api.model.entitys.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book,Long> {
    Page<Book> findAllByActivatedTrue(Pageable pageable);
}
