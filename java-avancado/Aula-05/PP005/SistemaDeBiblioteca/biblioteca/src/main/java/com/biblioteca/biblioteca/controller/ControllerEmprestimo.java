package com.biblioteca.biblioteca.controller;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
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

import com.biblioteca.biblioteca.controller.DTO.EmprestimoDTO;
import com.biblioteca.biblioteca.controller.FORM.EmprestimoFORM;
import com.biblioteca.biblioteca.controller.repository.ClienteRepository;
import com.biblioteca.biblioteca.controller.repository.EmprestimoRepository;
import com.biblioteca.biblioteca.controller.repository.LivroRepository;
import com.biblioteca.biblioteca.model.Emprestimo;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/emprestimos/")
public class ControllerEmprestimo {

    private static final Logger log = LoggerFactory.getLogger(ControllerEmprestimo.class);

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private LivroRepository livroRepository;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody EmprestimoFORM emprestimoForm, UriComponentsBuilder uriBuilder) {
        try {
            Emprestimo emprestimo = new Emprestimo();
            inicializarEmprestimo(emprestimoForm, emprestimo);
            emprestimoRepository.save(emprestimo);
            EmprestimoDTO emprestimoDTO = new EmprestimoDTO(emprestimo);
            URI uri = uriBuilder.path("/emprestimos/{id}").buildAndExpand(emprestimo.getId()).toUri();
            log.info("[CREATE] Empréstimo criado: {}", emprestimo);
            return ResponseEntity.created(uri).body(emprestimoDTO);
        } catch (DataIntegrityViolationException e) {
            String detalheErro = extrairDetalheErro(e.getMessage());
            log.error("[CREATE] Erro ao criar empréstimo: {}", detalheErro);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(detalheErro);
        } catch (Exception e) {
            log.error("CREATE] Erro ao criar empréstimo: {} ", e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    private String extrairDetalheErro(String mensagemErro) {
        if (mensagemErro.contains("(cliente_id)")) {
            return "O cliente especificado não foi encontrado.";
        } else if (mensagemErro.contains("(livro_id)")) {
            return "O livro especificado não foi encontrado.";
        } else {
            return "Erro ao criar empréstimo: " + mensagemErro;
        }
    }

    private void inicializarEmprestimo(EmprestimoFORM emprestimoForm, Emprestimo emprestimo) {
        emprestimo.setCliente(clienteRepository.getReferenceById(emprestimoForm.id_cliente()));
        emprestimo.setLivro(livroRepository.getReferenceById(emprestimoForm.id_livro()));
        emprestimo.setData_emprestimo(LocalDateTime.now());
    }

    @GetMapping
    public List<EmprestimoDTO> buscarTodosEmprestimos() {
        log.info("[READ] Todos os empréstimos pesquisados.");
        return emprestimoRepository.findAll().stream().map(EmprestimoDTO::new).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarEmprestimoPorId(@PathVariable Long id) {
        try {
            Emprestimo emprestimo = emprestimoRepository.getReferenceById(id);
            EmprestimoDTO emprestimoDTO = new EmprestimoDTO(emprestimo);
            log.info("[READ] Empréstimo pesquisado: {}", emprestimo);
            return ResponseEntity.ok(emprestimoDTO);
        } catch (EntityNotFoundException e) {
            log.error("[READ] Erro ao buscar empréstimo de id {} - Empréstimo não encontrado : {}", id, e.getMessage());
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            log.error("[READ] Erro ao buscar empréstimo: {}", e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody EmprestimoFORM emprestimoForm) {
        try {
            Emprestimo emprestimoAntes = emprestimoRepository.getReferenceById(id);
            Emprestimo emprestimo = new Emprestimo(emprestimoAntes);
            atualizarEmprestimo(emprestimoForm, emprestimo);
            emprestimoRepository.save(emprestimo);
            EmprestimoDTO emprestimoDTO = new EmprestimoDTO(emprestimo);
            log.info("[UPDATE] Empréstimo antes da atualização: {} | Empréstimo atualizado: {}", emprestimoAntes,
                    emprestimo);
            return ResponseEntity.ok(emprestimoDTO);
        } catch (EntityNotFoundException e) {
            log.error("[UPDATE] Erro ao atualizar empréstimo - Empréstimo não encontrado: {}", e.getMessage());
            return ResponseEntity.notFound().build();
        } catch (DataIntegrityViolationException e) {
            String detalheErro = extrairDetalheErro(e.getMessage());
            log.error("[UPDATE] Erro ao atualizar empréstimo: {}", detalheErro);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(detalheErro);
        } catch (Exception e) {
            log.error("UPDATE] Erro ao atualizar empréstimo: {} ", e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    private void atualizarEmprestimo(EmprestimoFORM emprestimoForm, Emprestimo emprestimo) {
        emprestimo.setCliente(clienteRepository.getReferenceById(emprestimoForm.id_cliente()));
        emprestimo.setLivro(livroRepository.getReferenceById(emprestimoForm.id_livro()));
        emprestimo.setData_emprestimo(emprestimoForm.data_emprestimo());
        emprestimo.setData_devolucao(emprestimo.getData_devolucao());
    }

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		try {
			Emprestimo emprestimo = emprestimoRepository.getReferenceById(id);
			emprestimoRepository.delete(emprestimo);
			EmprestimoDTO emprestimoDTO = new EmprestimoDTO(emprestimo);
			log.info("[DELETE] Empréstimo excluído: {}", emprestimo);
			return ResponseEntity.ok(emprestimoDTO);
		} catch (EntityNotFoundException e) {
			log.error("[DELETE] Erro ao excluir empréstimo - Empréstimo não encontrado: {}", e.getMessage());
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			log.error("[DELETE] Erro ao excluir empréstimo: {}", e.getMessage());
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PutMapping("/{id}/devolucao")
	public ResponseEntity<?> devolucao(@PathVariable Long id) {
	    try {
	        Emprestimo emprestimo = emprestimoRepository.getReferenceById(id);
	        if (emprestimo.getData_devolucao() != null) {
		        log.error("[DEVOLUCAO] Erro ao registrar devolução - empréstimo já finalizado");
	            return ResponseEntity.badRequest().body("Esse empréstimo já foi finalizado.");
	        }
	        emprestimo.setData_devolucao(LocalDateTime.now());
	        emprestimoRepository.save(emprestimo);
	        EmprestimoDTO emprestimoDTO = new EmprestimoDTO(emprestimo);
	        log.info("[DEVOLUCAO] Empréstimo devolvido: {}", emprestimo);
	        return ResponseEntity.ok(emprestimoDTO);
	    } catch (EntityNotFoundException e) {
	        log.error("[DEVOLUCAO] Erro ao registrar devolução - Empréstimo não encontrado: {}", e.getMessage());
	        return ResponseEntity.notFound().build();
	    } catch (Exception e) {
	        log.error("[DEVOLUCAO] Erro ao registrar devolução: {}", e.getMessage());
	        return ResponseEntity.badRequest().body("Ocorreu um erro ao registrar a devolução do empréstimo.");
	    }
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
		List<String> errors = new ArrayList<>();
		ex.getBindingResult().getFieldErrors().forEach(error -> errors.add(error.getDefaultMessage()));
		ErrorResponse errorResponse = new ErrorResponse(errors);
		return ResponseEntity.badRequest().body(errorResponse);
	}

}
