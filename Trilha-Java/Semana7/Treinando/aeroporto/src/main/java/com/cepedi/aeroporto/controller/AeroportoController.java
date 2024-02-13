package com.cepedi.aeroporto.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cepedi.aeroporto.controller.DTO.AeroportoDTO;
import com.cepedi.aeroporto.repository.AeroportoRepository;

@Controller
public class AeroportoController {

	@Autowired
	private AeroportoRepository aeroportoRepository;
	
	
	@RequestMapping("/listaaeroportos")
	@ResponseBody
	public List<AeroportoDTO> bucaAeroportos() {
	    return aeroportoRepository.findAll().stream().map(AeroportoDTO::new).collect(Collectors.toList());
	}


	
}
