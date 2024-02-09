package br.com.cepedi.vercedoresFormula1.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.cepedi.vercedoresFormula1.model.WinnerTDO;

@Controller
public class ControllerQuerys {

    private static List<WinnerTDO> vencedores;

    public ControllerQuerys() {
        vencedores = WinnerTDOReader.readWinners();
    }

    @RequestMapping("/todos")
    @ResponseBody
    public String todosVencedores() {
        return Display.exibirVencedores(vencedores);
    }

    @RequestMapping("/brasileiros")
    @ResponseBody
    public String todosVencedoresBrasileiros() {
        return Display.exibirVencedores(QuerysCountry.filtrarVencedoresPorPais("Brasil",vencedores));
    }

    @RequestMapping("/top5")
    @ResponseBody
    public String top5() {
        List<WinnerTDO> top5Winners = QuerysWinners.getTopNVencedores(5,vencedores);
        return Display.exibirVencedores(top5Winners);
    }
    
    @RequestMapping("/top10")
    @ResponseBody
    public String top10() {
        List<WinnerTDO> top10Winners = QuerysWinners.getTopNVencedores(10,vencedores);
        return Display.exibirVencedores(top10Winners);
    }
    
    @RequestMapping("/porpais")
    @ResponseBody
    public String porPais() {
        Map<String, Integer> listPorPais = QuerysCountry.victoryPorPais(vencedores);
        Map<String, Integer> mapaOrdenadoPorValor = Utils.ordenarPorValor(listPorPais);
        return Display.exibirPaises(mapaOrdenadoPorValor);
    }
    
    @RequestMapping("/mediaporpais")
    @ResponseBody
    public String mediaPorPais() {
        Map<String, Integer> listPorPais = QuerysCountry.victoryPorPais(vencedores);
        Map<String, Double> medias = QuerysCountry.buscaMediaPorPais(listPorPais,vencedores);
        Map<String, Double> mapaOrdenadoPorValor = Utils.ordenarPorValor(medias);
        return Display.exibirPaises(mapaOrdenadoPorValor);
    }
    

    


    

   


    
}
