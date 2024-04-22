package br.com.cepedi.Business.api.controller.V1;


import br.com.cepedi.Business.api.model.records.role.details.DataDetailsRole;
import br.com.cepedi.Business.api.model.records.role.input.DataRegisterRole;
import br.com.cepedi.Business.api.model.records.role.input.DataUpdateRole;
import br.com.cepedi.Business.api.service.role.RoleService;
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
@RequestMapping("v1/employees/roles")
@SecurityRequirement(name = "bearer-key")
public class RoleController {

    private static final Logger logger = LoggerFactory.getLogger(ClientController.class);


    @Autowired
    private RoleService service;

    @PostMapping
    @Transactional
    public ResponseEntity<DataDetailsRole> register(@RequestBody @Valid DataRegisterRole data , UriComponentsBuilder uriBuilder){
        logger.info("Registering new role...");
        DataDetailsRole dataDetailsRole = service.register(data);
        URI uri = uriBuilder.path("/employees/roles/{id}").buildAndExpand(dataDetailsRole.id()).toUri();
        logger.info("New role registered with ID: {}", dataDetailsRole.id());
        return ResponseEntity.created(uri).body(dataDetailsRole);
    }

    @GetMapping
    public ResponseEntity<Page<DataDetailsRole>> list(@PageableDefault(size = 10, sort = {"name"}) Pageable pageable){
        logger.info("Fetching list of role...");
        Page<DataDetailsRole> page = service.list(pageable);
        logger.info("List of role fetched successfully.");
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataDetailsRole> details(@PathVariable Long id){
        logger.info("Fetching role of supplier with ID: {}", id);
        DataDetailsRole details = service.findById(id);
        logger.info("Details of role with ID {} fetched successfully.", id);
        return ResponseEntity.ok(details);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DataDetailsRole> update(@PathVariable Long id,  @RequestBody @Valid DataUpdateRole data){
        logger.info("Updating role with ID: {}", id);
        DataDetailsRole details = service.update(id , data);
        logger.info("Role with ID {} updated successfully.", id);
        return ResponseEntity.ok(details);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> disabled(@PathVariable Long id){
        logger.info("Disabling role with ID: {}", id);
        service.disabled(id);
        logger.info("Role with ID {} disabled successfully.", id);
        return ResponseEntity.noContent().build();
    }


}
