package com.cepedi.leilao.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cepedi.leilao.controller.DTO.LanceDTO;
import com.cepedi.leilao.repository.LanceRepository;

@RestController
@RequestMapping("/lance")
public class LanceController {

    @Autowired
    private LanceRepository lanceRepository;

    @GetMapping
    public ResponseEntity<List<LanceDTO>> getAllLances() {
        List<LanceDTO> lancesDTO = lanceRepository.findAll().stream().map(LanceDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok(lancesDTO);
    }
    
    
    
}
