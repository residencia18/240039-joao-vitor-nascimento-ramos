package com.biblioteca.biblioteca.controller;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@Autowired
	private EmprestimoRepository emprestimorepository;

	@Autowired
	private ClienteRepository clientesrepository;
	
	@Autowired
	private LivroRepository livrorepository;
	
    @PostMapping
    public ResponseEntity<?> create(@RequestBody EmprestimoFORM emprestimoForm , UriComponentsBuilder uriBuilder){
        try {
            Emprestimo emprestimo = new Emprestimo();
            iniciaEmprestimo(emprestimoForm, emprestimo);
            emprestimorepository.save(emprestimo);
            EmprestimoDTO emprestimoDTO = new EmprestimoDTO(emprestimo);
            uriBuilder.path("/emprestimos/{id}");
            URI uri = uriBuilder.buildAndExpand(emprestimo.getId()).toUri();
            return ResponseEntity.created(uri).body(emprestimoDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ocorreu um erro ao criar o empréstimo.");
        }
    }
    
    private void iniciaEmprestimo(EmprestimoFORM emprestimoForm , Emprestimo emprestimo) {
        emprestimo.setCliente(clientesrepository.getReferenceById(emprestimoForm.id_cliente()));
        emprestimo.setLivro(livrorepository.getReferenceById(emprestimoForm.id_livro()));
        emprestimo.setData_emprestimo(LocalDateTime.now());
    }
    
	@GetMapping
	public List<EmprestimoDTO> buscarTodosLivros() {
	    return emprestimorepository.findAll().stream().map(EmprestimoDTO::new).collect(Collectors.toList());
	}

	@GetMapping("/{id}")
	public ResponseEntity<EmprestimoDTO> buscarLivroPorId(@PathVariable Long id) {
	    Optional<Emprestimo> emprestimoOpcional = emprestimorepository.findById(id);
	    if (emprestimoOpcional.isPresent()) {
			EmprestimoDTO emprestimoDTO = new EmprestimoDTO(emprestimoOpcional.get());
	        return ResponseEntity.ok(emprestimoDTO);
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}
	
		//atualizaDataEmprestimo
	  @PutMapping("/{id}")
	    public ResponseEntity<?> update(@PathVariable Long id , @RequestBody EmprestimoFORM emprestimoForm){
	        try {
	            Optional<Emprestimo> emprestimoOptional = emprestimorepository.findById(id);
	            if (!emprestimoOptional.isPresent()) {
	                return ResponseEntity.notFound().build();
	            }
	            Emprestimo emprestimo = emprestimoOptional.get();
	            atualizaEmprestimo(emprestimoForm,emprestimo);	            
	            emprestimorepository.save(emprestimo);
	            EmprestimoDTO emprestimoDTO = new EmprestimoDTO(emprestimo);
	            return ResponseEntity.ok(emprestimoDTO);
	        } catch (Exception e) {
	            return ResponseEntity.badRequest().body("Ocorreu um erro ao atualizar o empréstimo.");
	        }
	    }
	  
	  
	    private void atualizaEmprestimo(EmprestimoFORM emprestimoForm , Emprestimo emprestimo) {
	        emprestimo.setCliente(clientesrepository.getReferenceById(emprestimoForm.id_cliente()));
	        emprestimo.setLivro(livrorepository.getReferenceById(emprestimoForm.id_livro()));
	        emprestimo.setData_emprestimo(emprestimoForm.data_emprestimo());
	        emprestimo.setData_devolucao(emprestimo.getData_devolucao());
	    }
	    
	
	  @DeleteMapping("/{id}")
	    public ResponseEntity<?> delete(@PathVariable Long id){
	        try {
	            Optional<Emprestimo> emprestimoOptional = emprestimorepository.findById(id);
	            if (!emprestimoOptional.isPresent()) {
	                return ResponseEntity.notFound().build();
	            }
	            Emprestimo emprestimo = emprestimoOptional.get();
	            emprestimorepository.delete(emprestimo);
	            EmprestimoDTO emprestimoDTO = new EmprestimoDTO(emprestimo);
	            return ResponseEntity.ok(emprestimoDTO);
	        } catch (Exception e ) {
	            return ResponseEntity.badRequest().body("Ocorreu um erro ao deletar o empréstimo.");
	        }
	    }
	    
	    @PutMapping("/{id}/devolucao")
	    public ResponseEntity<?> devolucao(@PathVariable Long id) {
	        try {
	            Optional<Emprestimo> emprestimoOptional = emprestimorepository.findById(id);
	            if (!emprestimoOptional.isPresent()) {
	                return ResponseEntity.notFound().build();
	            }
	            Emprestimo emprestimo = emprestimoOptional.get();
	            if (emprestimo.getData_devolucao() != null) {
	                return ResponseEntity.badRequest().body("Esse emprestimo já foi finalizado.");
	            }
	            emprestimo.setData_devolucao(LocalDateTime.now());
	            emprestimorepository.save(emprestimo);
	            EmprestimoDTO emprestimoDTO = new EmprestimoDTO(emprestimo);
	            return ResponseEntity.ok(emprestimoDTO);
	        } catch (Exception e) {
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
