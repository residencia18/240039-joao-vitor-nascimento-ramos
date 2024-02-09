package com.controleacademico.controleacademico.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ControllerVivo {
	
	private String atributo = "Ola";
	
	@RequestMapping("/vivo")
	@ResponseBody
	public String Atributo(){
		return atributo;
	}

}
