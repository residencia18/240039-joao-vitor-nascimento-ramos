package br.com.cepedi.Business.api.controller.V1;

import br.com.cepedi.Business.api.model.records.employee.details.DataDetailsEmployee;
import br.com.cepedi.Business.api.model.records.employee.input.DataRegisterEmployee;
import br.com.cepedi.Business.api.model.records.employee.input.DataUpdateEmployee;
import br.com.cepedi.Business.api.service.employee.EmployeeService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("v1/employees")
@SecurityRequirement(name = "bearer-key")
public class EmployeeController {

    private static final Logger logger = LoggerFactory.getLogger(ClientController.class);


    @Autowired
    private EmployeeService service;

    @PostMapping
    @Transactional
    public ResponseEntity<DataDetailsEmployee> register(@RequestBody @Valid DataRegisterEmployee data , UriComponentsBuilder uriBuilder){
        logger.info("Registering new employee...");
        DataDetailsEmployee dataDetailsEmployee = service.register(data);
        URI uri = uriBuilder.path("/employees/{id}").buildAndExpand(dataDetailsEmployee.id()).toUri();
        logger.info("New employee registered with ID: {}", dataDetailsEmployee.id());
        return ResponseEntity.created(uri).body(dataDetailsEmployee);
    }

    @GetMapping
    public ResponseEntity<Page<DataDetailsEmployee>> list(@PageableDefault(size = 10, sort = {"name"}) Pageable pageable){
        logger.info("Fetching list of employee...");
        Page<DataDetailsEmployee> page = service.list(pageable);
        logger.info("List of employee fetched successfully.");
        return ResponseEntity.ok(page);
    }


    @GetMapping("/{id}")
    public ResponseEntity<DataDetailsEmployee> details(@PathVariable Long id){
        logger.info("Fetching details of employee with ID: {}", id);
        DataDetailsEmployee details = service.findById(id);
        logger.info("Details of employee with ID {} fetched successfully.", id);
        return ResponseEntity.ok(details);
    }


    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DataDetailsEmployee> update(@PathVariable Long id , @RequestBody @Valid DataUpdateEmployee data){
        logger.info("Updating employee with ID: {}", id);
        DataDetailsEmployee details = service.update(id , data);
        logger.info("Employee with ID {} updated successfully.", id);
        return ResponseEntity.ok(details);
    }


    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> disabled(@PathVariable Long id){
        logger.info("Disabling employee with ID: {}", id);
        service.disabled(id);
        logger.info("Employee with ID {} disabled successfully.", id);
        return ResponseEntity.noContent().build();
    }

}
