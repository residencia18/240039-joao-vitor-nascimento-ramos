package br.com.cepedi.petshop.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
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

import br.com.cepedi.petshop.controller.DTO.PagamentoDTO;
import br.com.cepedi.petshop.controller.FORM.PagamentoFORM;
import br.com.cepedi.petshop.controller.repository.PagamentoRepository;
import br.com.cepedi.petshop.controller.repository.TipoPagamentoRepository;
import br.com.cepedi.petshop.controller.repository.VendaRepository;
import br.com.cepedi.petshop.model.Pagamento;
import br.com.cepedi.petshop.model.TipoPagamento;
import br.com.cepedi.petshop.model.Venda;

@RestController
@RequestMapping("/pagamentos/")
public class ControllerPagamento {

    @Autowired
    private PagamentoRepository pagamentoRepository;
    
	@Autowired
	private TipoPagamentoRepository tipoPagamentoRepository;
	
    @Autowired
    private VendaRepository vendaRepository;
	
	

    @PostMapping
    public ResponseEntity<?> create(@RequestBody PagamentoFORM pagamentoForm, UriComponentsBuilder uriBuilder) {
        try {
            // Busca o tipo de pagamento no banco de dados
            Optional<TipoPagamento> tipoPagamentoOptional = tipoPagamentoRepository.findById(pagamentoForm.getIdTipoPagamento());
            if (!tipoPagamentoOptional.isPresent()) {
                return ResponseEntity.badRequest().body("Tipo de pagamento não encontrado!");
            }
            TipoPagamento tipoPagamento = tipoPagamentoOptional.get();

            // Busca a venda no banco de dados
            Optional<Venda> vendaOptional = vendaRepository.findById(pagamentoForm.getIdVenda());
            if (!vendaOptional.isPresent()) {
                return ResponseEntity.badRequest().body("Venda não encontrada!");
            }
            Venda venda = vendaOptional.get();
            
            // Verifica se já existe um pagamento associado à venda
            Optional<Pagamento> pagamentoExistente = pagamentoRepository.findByVenda(venda);
            if (pagamentoExistente.isPresent()) {
                return ResponseEntity.badRequest().body("Já existe um pagamento associado a esta venda!");
            }

            // Cria o objeto Pagamento e o salva no repositório
            Pagamento pagamento = new Pagamento();
            pagamento.setTipoPagamento(tipoPagamento);
            pagamento.setVenda(venda);
            pagamentoRepository.save(pagamento);
            
            // Constrói a URI para a resposta
            URI uri = uriBuilder.path("/pagamentos/{id}").buildAndExpand(pagamento.getId()).toUri();
            
            // Retorna uma resposta com status 201 (Created) e o corpo contendo o DTO do pagamento criado
            return ResponseEntity.created(uri).body(new PagamentoDTO(pagamento));
        } catch (Exception e) {
            // Em caso de erro, retorna uma resposta com status 400 (Bad Request) e a mensagem de erro
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }



    @GetMapping
    public List<PagamentoDTO> readAll() {
        return pagamentoRepository.findAll().stream().map(PagamentoDTO::new).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PagamentoDTO> readById(@PathVariable Long id) {
        Optional<Pagamento> pagamentoOptional = pagamentoRepository.findById(id);
        if (pagamentoOptional.isPresent()) {
            Pagamento pagamento = pagamentoOptional.get();
            PagamentoDTO pagamentoDTO = new PagamentoDTO(pagamento);
            return ResponseEntity.ok().body(pagamentoDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody PagamentoFORM pagamentoForm) {
        try {
            Optional<Pagamento> pagamentoOptional = pagamentoRepository.findById(id);
            if (pagamentoOptional.isPresent()) {
                Pagamento pagamento = pagamentoOptional.get();
                
                Optional<TipoPagamento> tipoPagamentoOptional = tipoPagamentoRepository.findById(pagamentoForm.getIdTipoPagamento());
                if (!tipoPagamentoOptional.isPresent()) {
                    return ResponseEntity.badRequest().body("Tipo de pagamento não encontrado!");
                }
                TipoPagamento tipoPagamento = tipoPagamentoOptional.get();

                Optional<Venda> vendaOptional = vendaRepository.findById(pagamentoForm.getIdVenda());
                if (!vendaOptional.isPresent()) {
                    return ResponseEntity.badRequest().body("Venda não encontrada!");
                }
                Venda venda = vendaOptional.get();
                
                Optional<Pagamento> pagamentoExistente = pagamentoRepository.findByVenda(venda);
                if (pagamentoExistente.isPresent() && !pagamentoExistente.get().equals(pagamento)) {
                    return ResponseEntity.badRequest().body("Já existe um pagamento associado a esta venda!");
                }

                pagamento.setTipoPagamento(tipoPagamento);
                pagamento.setVenda(venda);

                pagamentoRepository.save(pagamento);
                PagamentoDTO pagamentoDTO = new PagamentoDTO(pagamento);
                return ResponseEntity.ok().body(pagamentoDTO);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            Optional<Pagamento> pagamentoOptional = pagamentoRepository.findById(id);
            if (pagamentoOptional.isPresent()) {
                Pagamento pagamento = pagamentoOptional.get();
                pagamentoRepository.delete(pagamento);
                PagamentoDTO pagamentoDTO = new PagamentoDTO(pagamento);
                return ResponseEntity.ok(pagamentoDTO);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
