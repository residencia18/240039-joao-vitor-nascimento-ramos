package com.controleacademico.controleacademico.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.controleacademico.controleacademico.model.RSAGenerator;

@Controller
public class RSAController {
	
    @RequestMapping("/gerachavesrsa")
    @ResponseBody
    public String gerandoChavesRSA() {
    	return (new RSAGenerator(256)).toString();
    }

    
    
//    public static ArrayList<Long> geradorChavesRSA(){
//    	Long P = geraAleatorioPrimoAcimaDe(122587430247524101L);
//    	Long Q = geraAleatorioPrimoAcimaDe(P);
//    	ArrayList<Long> chaves =  new ArrayList<>(List.of(P,Q));
//
//    	return chaves;
//    }
//    
//    
//    public static Long geraAleatorioPrimoAcimaDe(Long i) {
//    	i+=2;
//    	while(true) {
//            if (PrimosUtil.isPrimo(i)) return i;
//            i+=2;
//    	}
//    }

}
