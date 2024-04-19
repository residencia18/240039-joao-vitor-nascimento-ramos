package br.com.cepedi.Business.api.repository;

import br.com.cepedi.Business.api.model.entitys.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {

    Page<Employee> findAllByActivatedTrue(Pageable pageable);


    @Query("""
            SELECT e.activated FROM Employee e WHERE e.id = :id
            """)
    Boolean findActivatedById(Long id);

}
