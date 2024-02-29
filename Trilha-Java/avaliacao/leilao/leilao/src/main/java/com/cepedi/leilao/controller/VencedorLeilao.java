package com.cepedi.leilao.controller;

import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cepedi.leilao.controller.DTO.ConcorrenteDTO;
import com.cepedi.leilao.model.Lance;
import com.cepedi.leilao.model.Leilao;
import com.cepedi.leilao.repository.ConcorrenteRepository;
import com.cepedi.leilao.repository.LanceRepository;
import com.cepedi.leilao.repository.LeilaoRepository;

@RestController
@RequestMapping("/vencedor_leilao/")
public class VencedorLeilao {
    
    @Autowired
    private LanceRepository lanceRepository;
    
    @Autowired
    private LeilaoRepository leilaoRepository;
    
    
    @GetMapping("/{id}")
    public ResponseEntity<?> obterVencedorLeilao(@PathVariable Long id) {
        try {
            // Verifica se o ID do leilão foi fornecido
            if (id == null) {
                return ResponseEntity.badRequest().body("O ID do leilão não foi fornecido.");
            }

            // Busca o leilão pelo ID
            Optional<Leilao> leilaoOptional = leilaoRepository.findById(id);
            if (leilaoOptional.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            Leilao leilao = leilaoOptional.get();

            // Verifica se o leilão está fechado
            if (!leilao.getStatus()) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("O leilão está fechado.");
            }


            Lance maiorLance = lanceRepository.findTopByLeilaoOrderByValorDesc(leilao);
            if (maiorLance == null) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Não foi possível obter o vencedor do leilão.");
            }

            // Constrói o DTO do concorrente que fez o maior lance
            ConcorrenteDTO concorrenteDTO = new ConcorrenteDTO(maiorLance.getConcorrente());

            // Monta o JSON com os dados do leilão, o valor do maior lance e o DTO do concorrente
            JSONObject responseJson = new JSONObject();
            responseJson.put("id_leilao", leilao.getId());
            responseJson.put("descricao_leilao", leilao.getDescricao());
            responseJson.put("maior_lance", maiorLance.getValor());
            responseJson.put("concorrente", concorrenteDTO);

            return ResponseEntity.ok(responseJson.toString());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
