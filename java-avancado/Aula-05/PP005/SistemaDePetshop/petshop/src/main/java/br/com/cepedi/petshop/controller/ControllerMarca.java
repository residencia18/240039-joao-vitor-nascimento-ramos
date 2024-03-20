package br.com.cepedi.petshop.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/produtos/marcas/")
public class ControllerMarca {

    @Autowired
    private MarcaRepository marcaRepository;
    
    public static final Logger log = LoggerFactory.getLogger(ControllerMarca.class);
    
    @PostMapping
    public ResponseEntity<?> create(@RequestBody MarcaFORM marcaForm, UriComponentsBuilder uriBuilder){
        try {
            Marca marca = new Marca();
            construindoMarca(marcaForm, marca);
            marcaRepository.save(marca);
            MarcaDTO marcaDTO = new MarcaDTO(marca);
            uriBuilder.path("/produtos/marcas/{id}");
            URI uri = uriBuilder.buildAndExpand(marca.getId()).toUri();
            log.info("[CREATE] Marca criada: {}", marca);
            return ResponseEntity.created(uri).body(marcaDTO);
        } catch (Exception e) {
            String mensagemErro = String.format("Erro ao criar marca: %s", e.getMessage());
            log.error("[CREATE] " + mensagemErro);
            return ResponseEntity.badRequest().body(mensagemErro);
        }
    }

	private void construindoMarca(MarcaFORM marcaForm, Marca marca) {
		marca.setNome(marcaForm.nome());
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
        } catch (EntityNotFoundException e) {
            String mensagemErro = String.format("Erro ao buscar marca de id %d - Marca não encontrada : %s", id, e.getMessage());
            log.error("[READ] " + mensagemErro);
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            String mensagemErro = String.format("Erro ao buscar marca: %s", e.getMessage());
            log.error("[READ] " + mensagemErro);
            return ResponseEntity.badRequest().body(mensagemErro);
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody MarcaFORM marcaForm){
        try {
            Marca marca = marcaRepository.getReferenceById(id);
            construindoMarca(marcaForm, marca);
            marcaRepository.save(marca);
            MarcaDTO marcaDTO = new MarcaDTO(marca);
            log.info("[UPDATE] Marca atualizada: {}", marca);
            return ResponseEntity.ok(marcaDTO);
        } catch (EntityNotFoundException e) {
            String mensagemErro = String.format("Erro ao atualizar marca - Marca não encontrada: %s", e.getMessage());
            log.error("[UPDATE] " + mensagemErro);
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            String mensagemErro = String.format("Erro ao atualizar marca: %s", e.getMessage());
            log.error("[UPDATE] " + mensagemErro);
            return ResponseEntity.badRequest().body(mensagemErro);
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
        } catch (EntityNotFoundException e) {
            String mensagemErro = String.format("Erro ao excluir marca - Marca não encontrada: %s", e.getMessage());
            log.error("[DELETE] " + mensagemErro);
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            String mensagemErro = String.format("Erro ao excluir marca: %s", e.getMessage());
            log.error("[DELETE] " + mensagemErro);
            return ResponseEntity.badRequest().body(mensagemErro);
        }
    }
}
