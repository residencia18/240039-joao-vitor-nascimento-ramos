package br.com.cepedi.Library.api.repository;

import br.com.cepedi.Library.api.model.entitys.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author,Long> {
    Page<Author> findAllByActivatedTrue(Pageable pageable);
}
