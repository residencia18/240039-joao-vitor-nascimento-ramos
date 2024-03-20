package br.com.cepedi.petshop.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.cepedi.petshop.controller.DTO.MarcaDTO;
import br.com.cepedi.petshop.controller.FORM.MarcaFORM;
import br.com.cepedi.petshop.controller.repository.MarcaRepository;
import br.com.cepedi.petshop.model.Marca;

@RestController
@RequestMapping("/produtos/marcas/")
public class ControllerMarca {

    @Autowired
    private MarcaRepository marcaRepository;
    
    public static final Logger log = LoggerFactory.getLogger(ControllerMarca.class);
    
    @PostMapping
    public ResponseEntity<?> create(@RequestBody MarcaFORM marcaForm, UriComponentsBuilder uriBuilder){
        try {
            Marca marca = marcaForm.toMarca();
            marcaRepository.save(marca);
            MarcaDTO marcaDTO = new MarcaDTO(marca);
            uriBuilder.path("/produtos/marcas/{id}");
            URI uri = uriBuilder.buildAndExpand(marca.getId()).toUri();
            log.info("[CREATE] Marca criada: {}", marca);
            return ResponseEntity.created(uri).body(marcaDTO);
        } catch (Exception e) {
            log.error("[CREATE] Erro ao criar marca: {}", e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
    
    @GetMapping
    public List<MarcaDTO> readAll(){
        log.info("[READ] Todas marcas pesquisadas");
        return marcaRepository.findAll().stream().map(MarcaDTO::new).collect(Collectors.toList());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> readById(@PathVariable Long id) {
        try {
            Marca marca = marcaRepository.getReferenceById(id);
            MarcaDTO marcaDTO = new MarcaDTO(marca);
            log.info("[READ] Marca encontrada: {}", marca);
            return ResponseEntity.ok(marcaDTO);
        } catch (EmptyResultDataAccessException e) {
            log.error("[READ] Erro ao buscar marca de id {} - Marca não encontrada : {}", id, e.getMessage());
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            log.error("[READ] Erro ao buscar marca: {}", e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody MarcaFORM marcaForm){
        try {
            Marca marca = marcaRepository.getReferenceById(id);
            marca.setNome(marcaForm.getNome());
            marcaRepository.save(marca);
            MarcaDTO marcaDTO = new MarcaDTO(marca);
            log.info("[UPDATE] Marca atualizada: {}", marca);
            return ResponseEntity.ok(marcaDTO);
        } catch (EmptyResultDataAccessException e) {
            log.error("[UPDATE] Erro ao atualizar marca - Marca não encontrada: {}", e.getMessage());
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            log.error("[UPDATE] Erro ao atualizar marca: {}", e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        try {
            Marca marca = marcaRepository.getReferenceById(id);
            marcaRepository.delete(marca);
            MarcaDTO marcaDTO = new MarcaDTO(marca);
            log.info("[DELETE] Marca excluída: {}", marca);
            return ResponseEntity.ok(marcaDTO);
        } catch (EmptyResultDataAccessException e) {
            log.error("[DELETE] Erro ao excluir marca - Marca não encontrada: {}", e.getMessage());
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            log.error("[DELETE] Erro ao excluir marca: {}", e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
