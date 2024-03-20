package br.com.cepedi.petshop.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.cepedi.petshop.controller.DTO.ClienteDTO;
import br.com.cepedi.petshop.controller.FORM.ClienteFORM;
import br.com.cepedi.petshop.controller.repository.ClienteRepository;
import br.com.cepedi.petshop.exceptions.CPFInvalidoException;
import br.com.cepedi.petshop.exceptions.NomeInvalidoException;
import br.com.cepedi.petshop.model.Cliente;

@RestController
@RequestMapping("/clientes/")
public class ControllerCliente {

    public static final Logger log = LoggerFactory.getLogger(ControllerCliente.class);

    @Autowired
    ClienteRepository clientesrepository;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ClienteFORM clienteForm, UriComponentsBuilder uriBuilder) {
        try {
            Cliente cliente = new Cliente();
            construindoCliente(clienteForm, cliente);
            clientesrepository.save(cliente);
            ClienteDTO clienteDTO = new ClienteDTO(cliente);
            uriBuilder.path("/clientes/{id}");
            URI uri = uriBuilder.buildAndExpand(cliente.getId()).toUri();
            log.info("[CREATE] Cliente criado: {}", cliente);
            return ResponseEntity.created(uri).body(clienteDTO);
        } catch (Exception e) {
            String mensagemErro = String.format("Erro ao criar cliente: %s", e.getMessage());
            log.error("[CREATE] " + mensagemErro);
            return ResponseEntity.badRequest().body(mensagemErro);
        }
    }

    private void construindoCliente(ClienteFORM clienteForm, Cliente cliente) throws NomeInvalidoException, CPFInvalidoException {
        cliente.setNome(clienteForm.nome());
        cliente.setCpf(clienteForm.cpf());
    }

    @GetMapping
    public List<ClienteDTO> readAll() {
        log.info("[READ] Todos clientes pesquisados");
        return clientesrepository.findAll().stream().map(ClienteDTO::new).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> readById(@PathVariable Long id) {
        try {
            Cliente cliente = clientesrepository.getReferenceById(id);
            ClienteDTO clienteDTO = new ClienteDTO(cliente);
            log.info("[READ] Cliente pesquisado: {}", cliente);
            return ResponseEntity.ok(clienteDTO);
        } catch (EmptyResultDataAccessException e) {
            String mensagemErro = String.format("Erro ao buscar cliente de id %d - Cliente não encontrado : %s", id, e.getMessage());
            log.error("[READ] " + mensagemErro);
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            String mensagemErro = String.format("Erro ao buscar cliente: %s", e.getMessage());
            log.error("[READ] " + mensagemErro);
            return ResponseEntity.badRequest().body(mensagemErro);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody ClienteFORM clienteForm) {
        try {
            Cliente cliente = clientesrepository.getReferenceById(id);
            construindoCliente(clienteForm, cliente);
            clientesrepository.save(cliente);
            ClienteDTO clienteDTO = new ClienteDTO(cliente);
            log.info("[UPDATE] Cliente atualizado: {}", cliente);
            return ResponseEntity.ok(clienteDTO);
        } catch (EmptyResultDataAccessException e) {
            String mensagemErro = String.format("Erro ao atualizar cliente - Cliente não encontrado: %s", e.getMessage());
            log.error("[UPDATE] " + mensagemErro);
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            String mensagemErro = String.format("Erro ao atualizar cliente: %s", e.getMessage());
            log.error("[UPDATE] " + mensagemErro);
            return ResponseEntity.badRequest().body(mensagemErro);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            Cliente cliente = clientesrepository.getReferenceById(id);
            clientesrepository.delete(cliente);
            ClienteDTO clienteDTO = new ClienteDTO(cliente);
            log.info("[DELETE] Cliente excluído: {}", cliente);
            return ResponseEntity.ok(clienteDTO);
        } catch (Exception e) {
            String mensagemErro = String.format("Erro ao excluir cliente: %s", e.getMessage());
            log.error("[DELETE] " + mensagemErro);
            return ResponseEntity.notFound().build();
        }
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleException(Exception e) {
        log.error("Erro desconhecido: {}", e.getMessage());
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
