package com.cepedi.aeroporto.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cepedi.aeroporto.controller.DTO.PilotoDTO;
import com.cepedi.aeroporto.repository.PilotoRepository;

@Controller
public class PilotoController {

	@Autowired
	private PilotoRepository pilotoRepository;
	
	
    @RequestMapping("/listapilotos")
    @ResponseBody
	public List<PilotoDTO> buscaPilotos() {
		return pilotoRepository.findAll().stream().map(PilotoDTO::new).collect(Collectors.toList());
	}
	
}
