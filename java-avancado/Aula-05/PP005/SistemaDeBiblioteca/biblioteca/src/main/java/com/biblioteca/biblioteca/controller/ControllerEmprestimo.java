package com.biblioteca.biblioteca.controller;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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

@RestController
@RequestMapping("/emprestimos/")
public class ControllerEmprestimo {

	private static final Logger log = LoggerFactory.getLogger(ControllerEmprestimo.class);

	@Autowired
	private EmprestimoRepository emprestimorepository;

	@Autowired
	private ClienteRepository clientesrepository;

	@Autowired
	private LivroRepository livrorepository;

	@PostMapping
	public ResponseEntity<?> create(@RequestBody EmprestimoFORM emprestimoForm, UriComponentsBuilder uriBuilder) {
		try {
			Emprestimo emprestimo = new Emprestimo();
			iniciaEmprestimo(emprestimoForm, emprestimo);
			emprestimorepository.save(emprestimo);
			EmprestimoDTO emprestimoDTO = new EmprestimoDTO(emprestimo);
			uriBuilder.path("/emprestimos/{id}");
			URI uri = uriBuilder.buildAndExpand(emprestimo.getId()).toUri();
			log.info("[CREATE] Emprestimo criado: {}", emprestimo);
			return ResponseEntity.created(uri).body(emprestimoDTO);
		} catch (Exception e) {
			log.error("[CREATE] Erro ao criar emprestimo: {}", e.getMessage());
			return ResponseEntity.badRequest().body("Ocorreu um erro ao criar o empréstimo.");
		}
	}

	private void iniciaEmprestimo(EmprestimoFORM emprestimoForm, Emprestimo emprestimo) {
		emprestimo.setCliente(clientesrepository.getReferenceById(emprestimoForm.id_cliente()));
		emprestimo.setLivro(livrorepository.getReferenceById(emprestimoForm.id_livro()));
		emprestimo.setData_emprestimo(LocalDateTime.now());
	}

	@GetMapping
	public List<EmprestimoDTO> buscarTodosLivros() {
		log.info("[READ] Todas editoras pesquisadas");
		return emprestimorepository.findAll().stream().map(EmprestimoDTO::new).collect(Collectors.toList());
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> buscarLivroPorId(@PathVariable Long id) {
		try {
			Emprestimo emprestimo = emprestimorepository.getReferenceById(id);
			EmprestimoDTO emprestimoDTO = new EmprestimoDTO(emprestimo);
			log.info("[READ] Emprestimo pesquisado: {}", emprestimo);
			return ResponseEntity.ok(emprestimoDTO);
		} catch (EmptyResultDataAccessException e) {
			log.error("[READ] Erro ao buscar emprestimo de id {} - Editora não encontrada : {}", id, e.getMessage());
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			log.error("[READ] Erro ao buscar emprestimo: {}", e.getMessage());
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody EmprestimoFORM emprestimoForm) {
		try {
			Emprestimo emprestimoAntes = emprestimorepository.getReferenceById(id);
			Emprestimo emprestimo = new Emprestimo(emprestimoAntes);
			atualizaEmprestimo(emprestimoForm, emprestimo);
			emprestimorepository.save(emprestimo);
			EmprestimoDTO emprestimoDTO = new EmprestimoDTO(emprestimo);
			log.info("[UPDATE] Empréstimo antes da atualização: {} | Empréstimo atualizado: {}", emprestimoAntes,
					emprestimo);
			return ResponseEntity.ok(emprestimoDTO);
		} catch (EmptyResultDataAccessException e) {
			log.error("[UPDATE] Erro ao atualizar empréstimo - Empréstimo não encontrado: {}", e.getMessage());
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			log.error("[UPDATE] Erro ao atualizar empréstimo: {}", e.getMessage());
			return ResponseEntity.badRequest().body("Ocorreu um erro ao atualizar o empréstimo.");
		}
	}

	private void atualizaEmprestimo(EmprestimoFORM emprestimoForm, Emprestimo emprestimo) {
		emprestimo.setCliente(clientesrepository.getReferenceById(emprestimoForm.id_cliente()));
		emprestimo.setLivro(livrorepository.getReferenceById(emprestimoForm.id_livro()));
		emprestimo.setData_emprestimo(emprestimoForm.data_emprestimo());
		emprestimo.setData_devolucao(emprestimo.getData_devolucao());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		try {
			Emprestimo emprestimo = emprestimorepository.getReferenceById(id);
			emprestimorepository.delete(emprestimo);
			EmprestimoDTO emprestimoDTO = new EmprestimoDTO(emprestimo);
			log.info("[DELETE] Empréstimo excluído: {}", emprestimo);
			return ResponseEntity.ok(emprestimoDTO);
		} catch (EmptyResultDataAccessException e) {
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
	        Emprestimo emprestimo = emprestimorepository.getReferenceById(id);
	        if (emprestimo.getData_devolucao() != null) {
		        log.error("[DEVOLUCAO] Erro ao registrar devolução - emprestimo já finalizado");
	            return ResponseEntity.badRequest().body("Esse empréstimo já foi finalizado.");
	        }
	        emprestimo.setData_devolucao(LocalDateTime.now());
	        emprestimorepository.save(emprestimo);
	        EmprestimoDTO emprestimoDTO = new EmprestimoDTO(emprestimo);
	        log.info("[DEVOLUCAO] Empréstimo devolvido: {}", emprestimo);
	        return ResponseEntity.ok(emprestimoDTO);
	    } catch (EmptyResultDataAccessException e) {
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
