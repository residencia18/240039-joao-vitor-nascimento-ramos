package br.com.cepedi.main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;

import br.com.cepedi.model.Estudante;


public class LerEstudantesJSON {

    public static void main(String[] args) {
        List<Estudante> estudantes = new ArrayList<>();
        lerArquivo(estudantes);
        System.out.println(estudantes);
    }

    private static void lerArquivo(List<Estudante> estudantes) {
    	
        JSONArray json = new JSONArray();
        try {
            FileReader fr = new FileReader("./DadosJSON/LEstudantes.json");
            BufferedReader br = new BufferedReader(fr);
            if (!br.ready()) {
                return;
            }
            json = new JSONArray(br.readLine());
            br.close();
        } catch (IOException e) {
            System.out.println("Erro ao abrir arquivo de estudantes para leitura: " + e.getMessage());
        }
        
        

        for (int i = 0; i < json.length(); i++) {
            String nome = json.getJSONObject(i).getString("nome");
            int idade = json.getJSONObject(i).getInt("idade");
            String curso = json.getJSONObject(i).getString("curso");
            Estudante e = new Estudante(nome,idade,curso);
            estudantes.add(e);
        }
    	
    }
}

