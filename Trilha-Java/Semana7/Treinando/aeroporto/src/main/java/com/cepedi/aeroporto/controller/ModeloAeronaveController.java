package com.cepedi.aeroporto.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cepedi.aeroporto.controller.DTO.ModeloAeronaveDTO;
import com.cepedi.aeroporto.repository.ModeloAeronavelRepository;

@Controller
public class ModeloAeronaveController {

	@Autowired
	private ModeloAeronavelRepository modeloAeronaveRepository;
	
	
    @RequestMapping("/listamodeloaeronaves")
    @ResponseBody
	public List<ModeloAeronaveDTO> buscaModelos() {
		return modeloAeronaveRepository.findAll().stream().map(ModeloAeronaveDTO::new).collect(Collectors.toList());
	}
	
}
