package com.controleacademico.controleacademico.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ControllerArquivoTexto {

    private String texto;

    @RequestMapping("/arquivo")
    @ResponseBody
    public String Atributo() {
        return abreArquivoERetornaTexto();
    }

    private String abreArquivoERetornaTexto() {
        try {
            // Change the file path to the location of your text file
            String filePath = "arquivo.txt";
            texto = new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return texto;
    }
}
