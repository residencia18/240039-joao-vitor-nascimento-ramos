package com.controleacademico.controleacademico.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MenssegerController {

	
	@RequestMapping("/")
	@ResponseBody
	public String Hello() {
		return "Olá, entra na -> /laio";
	}
	
	@RequestMapping("/laio")
	@ResponseBody
	public String HelloLaio() {
		return "PI-011 hoje?";
	}
	
	@RequestMapping("/palavra")
	@ResponseBody
	public String HelloAlbert() {
		return "cobra";
	}
}
