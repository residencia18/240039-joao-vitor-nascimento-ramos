package br.com.cepedi.petshop.controller;

import java.math.BigDecimal;
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

import br.com.cepedi.petshop.controller.DTO.Venda_ProdutoDTO;
import br.com.cepedi.petshop.controller.FORM.Venda_ProdutoFORM;
import br.com.cepedi.petshop.controller.repository.PagamentoRepository;
import br.com.cepedi.petshop.controller.repository.ProdutoRepository;
import br.com.cepedi.petshop.controller.repository.VendaRepository;
import br.com.cepedi.petshop.controller.repository.Venda_ProdutoRepository;
import br.com.cepedi.petshop.model.Pagamento;
import br.com.cepedi.petshop.model.Produto;
import br.com.cepedi.petshop.model.Venda;
import br.com.cepedi.petshop.model.Venda_Produto;

@RestController
@RequestMapping("/vendas/{id}/produtos/")
public class ControllerVenda_Produto {

    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private Venda_ProdutoRepository vendaProdutoRepository;
    
    @Autowired
    private PagamentoRepository pagamentoRepository;

    @PostMapping
    public ResponseEntity<?> adicionarProdutoAVenda(@PathVariable Long id, @RequestBody Venda_ProdutoFORM vendaProdutoForm,
            UriComponentsBuilder uriBuilder) {
        try {
            Optional<Venda> vendaOptional = vendaRepository.findById(id);
            if (vendaOptional.isPresent()) {
                Venda venda = vendaOptional.get();
                
                Optional<Pagamento> pagamentoExistente = pagamentoRepository.findByVenda(venda);
                if (pagamentoExistente.isPresent()) {
                    return ResponseEntity.badRequest().body("Não é possível adicionar produtos a uma venda que já possui pagamento.");
                }
                
                Optional<Produto> produtoOptional = produtoRepository.findById(vendaProdutoForm.idProduto());
                if (produtoOptional.isPresent()) {
                    Produto produto = produtoOptional.get();
                    Venda_Produto vendaProduto = new Venda_Produto();
                    vendaProduto.setVenda(venda);
                    vendaProduto.setProduto(produto);
                    vendaProduto.setQuantidade(vendaProdutoForm.quantidade());
                    BigDecimal valor = produto.getPreco().multiply(new BigDecimal(vendaProdutoForm.quantidade()));
                    venda.adicionarValor(valor);
                    vendaRepository.save(venda);
                    vendaProdutoRepository.save(vendaProduto);
                    Venda_ProdutoDTO VendaProdutoDTO = new Venda_ProdutoDTO(vendaProduto);
                    uriBuilder.path("/vendas/"+id+"/produtos/{id}");
                    URI uri = uriBuilder.buildAndExpand(vendaProduto.getId()).toUri();
                    return ResponseEntity.created(uri).body(VendaProdutoDTO);
                } else {
                    return ResponseEntity.badRequest().body("Produto não encontrado!");
                }
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @GetMapping
    public ResponseEntity<?> findAll(@PathVariable Long id) {
        try {
            Optional<Venda> vendaOptional = vendaRepository.findById(id);
            if (vendaOptional.isPresent()) {
                List<Venda_Produto> produtos = vendaProdutoRepository.findByVendaId(id);
                if (!produtos.isEmpty()) {
                    List<Venda_ProdutoDTO> produtosDTO = produtos.stream().map(Venda_ProdutoDTO::new).collect(Collectors.toList());
                    return ResponseEntity.ok(produtosDTO);
                } else {
                    return ResponseEntity.notFound().build();
                }
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{produtoId}")
    public ResponseEntity<?> findById(@PathVariable Long id, @PathVariable Long produtoId) {
        try {
            Optional<Venda> vendaOptional = vendaRepository.findById(id);
            if (vendaOptional.isPresent()) {
                Optional<Venda_Produto> vendaProdutoOptional = vendaProdutoRepository.findById(produtoId);
                if (vendaProdutoOptional.isPresent()) {
                    Venda_ProdutoDTO produtoDTO = new Venda_ProdutoDTO(vendaProdutoOptional.get());
                    return ResponseEntity.ok(produtoDTO);
                } else {
                    return ResponseEntity.notFound().build();
                }
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @PutMapping("/{produtoId}")
    public ResponseEntity<?> update(@PathVariable Long id ,
            @PathVariable Long produtoId ,
            @RequestBody Venda_ProdutoFORM vendaProdutoForm ){
        
        try {
            Optional<Venda> vendaOptional = vendaRepository.findById(id);
            if(vendaOptional.isPresent()) {
                Optional<Venda_Produto> vendaProdutoOptional = vendaProdutoRepository.findById(produtoId);
                Optional<Produto> produtoOptional = produtoRepository.findById(vendaProdutoForm.idProduto());
                if (vendaProdutoOptional.isPresent() && produtoOptional.isPresent()) {
                    Produto produto = produtoOptional.get();
                 
                    Venda venda = vendaOptional.get();
                    Venda_Produto vendaProdutoAtual = vendaProdutoOptional.get();
                    
                    // Verifica se já existe um pagamento associado à venda
                    Optional<Pagamento> pagamentoExistente = pagamentoRepository.findByVenda(venda);
                    if (pagamentoExistente.isPresent()) {
                        return ResponseEntity.badRequest().body("Não é possível atualizar produtos de uma venda que já possui pagamento.");
                    }
                    
                    // Retira o valor atual daquele venda produto do venda para adicionar o novo
                    BigDecimal valorARetirar = vendaProdutoAtual.getProduto().getPreco().multiply(new BigDecimal(vendaProdutoAtual.getQuantidade()));
                    BigDecimal valorAdicionar = produto.getPreco().multiply(new BigDecimal(vendaProdutoForm.quantidade()));
                    venda.retiraValor(valorARetirar);
                    venda.adicionarValor(valorAdicionar);
                    
                    vendaProdutoAtual.setProduto(produto);
                    vendaProdutoAtual.setVenda(venda);
                    vendaProdutoAtual.setQuantidade(vendaProdutoForm.quantidade());
                    
                    vendaRepository.save(venda);
                    vendaProdutoRepository.save(vendaProdutoAtual);

                    Venda_ProdutoDTO produtoDTO = new Venda_ProdutoDTO(vendaProdutoOptional.get());
                    return ResponseEntity.ok(produtoDTO);
                } else {
                    return ResponseEntity.notFound().build();
                }
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @DeleteMapping("/{produtoId}")
    public ResponseEntity<?> delete(@PathVariable Long id ,
            @PathVariable Long produtoId  ){
        try {
            Optional<Venda> vendaOptional = vendaRepository.findById(id);
            if(vendaOptional.isPresent()) {
                Optional<Venda_Produto> vendaProdutoOptional = vendaProdutoRepository.findById(produtoId);
                if (vendaProdutoOptional.isPresent()) {
                 
                    Venda venda = vendaOptional.get();
                    Venda_Produto vendaProdutoAtual = vendaProdutoOptional.get();
                    

                 // Retira o valor atual daquele venda produto do venda para adicionar o novo
                    BigDecimal valorARetirar = vendaProdutoAtual.getProduto().getPreco().multiply(new BigDecimal(vendaProdutoAtual.getQuantidade()));
                    venda.retiraValor(valorARetirar);
                    

                    
                    vendaRepository.save(venda);
                    vendaProdutoRepository.delete(vendaProdutoAtual);

                    Venda_ProdutoDTO produtoDTO = new Venda_ProdutoDTO(vendaProdutoOptional.get());
                    return ResponseEntity.ok(produtoDTO);
                } else {
                    return ResponseEntity.notFound().build();
                }
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
