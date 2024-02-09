package com.controleacademico.controleacademico.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RandomController {

    @RequestMapping("/aleatorios")
    @ResponseBody
    public ArrayList<Integer> Aleatorios() {
        return AleatoriosUtil.geraAletorios();
    }
    
   
    @RequestMapping("/sequencia")
    @ResponseBody
    public ArrayList<Integer> AleatoriosEmSequencia() {
        return AleatoriosUtil.geraAletoriosEmSequencia();
    }



    @RequestMapping("/primos")
    @ResponseBody
    public ArrayList<Integer> AleatoriosPrimosEmSequencia() {
        return PrimosUtil.geraAletoriosPrimosEmSequencia();
    }
    



}
