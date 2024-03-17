package br.com.cepedi.petshop.controller;

import java.net.URI;
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

import com.biblioteca.biblioteca.controller.ErrorResponse;

import br.com.cepedi.petshop.controller.DTO.ProdutoDTO;
import br.com.cepedi.petshop.controller.FORM.ProdutoFORM;
import br.com.cepedi.petshop.controller.repository.MarcaRepository;
import br.com.cepedi.petshop.controller.repository.ProdutoRepository;
import br.com.cepedi.petshop.controller.repository.TipoProdutoRepository;
import br.com.cepedi.petshop.exceptions.PrecoInvalidoException;
import br.com.cepedi.petshop.model.Marca;
import br.com.cepedi.petshop.model.Produto;
import br.com.cepedi.petshop.model.TipoProduto;

@RestController
@RequestMapping("/produtos/")
public class ControllerProduto {
    
    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private TipoProdutoRepository tipoProdutoRepository;
    
    @Autowired
    private MarcaRepository marcaRepository;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ProdutoFORM produtoForm, UriComponentsBuilder uriBuilder) {
        try {
            Optional<TipoProduto> tipoProdutoOptional = tipoProdutoRepository.findById(produtoForm.idTipoProduto());
            Optional<Marca> marcaOptional = marcaRepository.findById(produtoForm.idMarca());
            
            if (tipoProdutoOptional.isPresent() && marcaOptional.isPresent()) {
                TipoProduto tipoProduto = tipoProdutoOptional.get();
                Marca marca = marcaOptional.get();
                Produto produto = new Produto();
                construindoProduto(produtoForm, tipoProduto, marca, produto);
                produtoRepository.save(produto);
                
                ProdutoDTO produtoDTO = new ProdutoDTO(produto);
                URI uri = uriBuilder.path("/produtos/{id}").buildAndExpand(produto.getId()).toUri();
                return ResponseEntity.created(uri).body(produtoDTO);
            } else {
                if (!tipoProdutoOptional.isPresent()) {
                    return ResponseEntity.badRequest().body("Tipo de produto não encontrado!");
                }
                
                return ResponseEntity.badRequest().body("Marca não encontrada!");
            }

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


	private void construindoProduto(ProdutoFORM produtoForm, TipoProduto tipoProduto, Marca marca, Produto produto)
			throws PrecoInvalidoException {
		produto.setNome(produtoForm.nome());
		produto.setPreco(produtoForm.preco());
		produto.setDescricao(produtoForm.descricao());
		produto.setTipoProduto(tipoProduto);
		produto.setMarca(marca);
	}

    
    @GetMapping
    public List<ProdutoDTO> readAll(){
    	return produtoRepository.findAll().stream().map(ProdutoDTO::new).collect(Collectors.toList());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDTO> readById(@PathVariable Long id){
    	
    	Optional<Produto> produtoOptional = produtoRepository.findById(id);
    	if(produtoOptional.isPresent()) {
    		Produto produto = produtoOptional.get();
    		ProdutoDTO produtoDTO = new ProdutoDTO(produto);
    		return ResponseEntity.ok().body(produtoDTO);
    	}else {
    		return ResponseEntity.notFound().build();
    	}
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody ProdutoFORM produtoForm){
        try {
            Optional<Produto> produtoOptional = produtoRepository.findById(id);
            Optional<TipoProduto> tipoProdutoOptionalFORM = tipoProdutoRepository.findById(produtoForm.idTipoProduto());
            Optional<Marca> marcaOptional = marcaRepository.findById(produtoForm.idMarca());

            if (produtoOptional.isPresent() && tipoProdutoOptionalFORM.isPresent() && marcaOptional.isPresent()) {
                Produto produto = produtoOptional.get();
                TipoProduto tipoProdutoFORM = tipoProdutoOptionalFORM.get();
                Marca marcaFORM = marcaOptional.get();
                
                construindoProduto(produtoForm, tipoProdutoFORM, marcaFORM, produto);
                produtoRepository.save(produto);
                
                ProdutoDTO produtoDTO = new ProdutoDTO(produto);
                return ResponseEntity.ok().body(produtoDTO);
            } else {
                if (!produtoOptional.isPresent()) {
                    return ResponseEntity.notFound().build();
                }
                if (!tipoProdutoOptionalFORM.isPresent()) {
                    return ResponseEntity.badRequest().body("Tipo de produto não encontrado!");
                }
                
                return ResponseEntity.badRequest().body("Marca não encontrada!");
                
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }




	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
	    try {
	        Optional<Produto> produtoOptional = produtoRepository.findById(id);
	        if (produtoOptional.isPresent()) {
	            Produto produto = produtoOptional.get();
	            produtoRepository.delete(produto);
	            ProdutoDTO produtoDTO = new ProdutoDTO(produto);
	            return ResponseEntity.ok(produtoDTO);
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    } catch (Exception e) {
	        return ResponseEntity.badRequest().body(e.getMessage());
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