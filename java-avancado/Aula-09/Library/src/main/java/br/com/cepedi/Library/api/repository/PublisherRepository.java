package br.com.cepedi.Library.api.repository;

import br.com.cepedi.Library.api.model.entitys.Publisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PublisherRepository extends JpaRepository<Publisher,Long> {
    Page<Publisher> findAllByActivatedTrue(Pageable pageable);
}
