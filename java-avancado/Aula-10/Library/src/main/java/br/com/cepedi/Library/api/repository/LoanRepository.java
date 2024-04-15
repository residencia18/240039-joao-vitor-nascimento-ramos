package br.com.cepedi.Library.api.repository;

import br.com.cepedi.Library.api.model.entitys.Loan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface LoanRepository extends JpaRepository<Loan,Long> {


    Page<Loan> findAllByActivatedTrue(Pageable pageable);

    @Query("""
            SELECT l.activated FROM Loan l WHERE l.id = :id
            """)
    Boolean findActivatedById(Long id);
}
