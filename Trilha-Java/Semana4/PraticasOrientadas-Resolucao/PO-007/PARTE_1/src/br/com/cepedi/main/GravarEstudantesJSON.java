package br.com.cepedi.main;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.cepedi.model.Estudante;


public class GravarEstudantesJSON {

    public static void main(String[] args) {
        List<Estudante> estudantes = new ArrayList<>();

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("Digite o nome do estudante (ou 'sair' para encerrar):");
                String nome = scanner.nextLine();

                if ("sair".equalsIgnoreCase(nome)) {
                    break;
                }

                System.out.println("Digite a idade do estudante:");
                int idade = scanner.nextInt();
                scanner.nextLine(); // Limpar o buffer

                System.out.println("Digite o curso do estudante:");
                String curso = scanner.nextLine();

                Estudante estudante = new Estudante(nome, idade, curso);
                estudantes.add(estudante);
            }
        }

        gravarEstudantesParaArquivo(estudantes);
        System.out.println("Dados dos estudantes foram gravados no arquivo 'estudantes.json'.");
    }

    private static void gravarEstudantesParaArquivo(List<Estudante> estudantes) {
		
		JSONArray json = new JSONArray();

		for (Estudante estudante : estudantes) {
			JSONObject o = new JSONObject();
			o.put("nome",estudante.getNome());
			o.put("idade",estudante.getIdade());
			o.put("curso",estudante.getCurso());
			

            json.put(o);
		}

		try {
			FileWriter fw = new FileWriter("./DadosJSON/LEstudantes.json");
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(json.toString());
			bw.close();
		} catch (IOException e) {
			System.out.println("Erro ao abrir arquivo de estudantes para escrita: " + e.getMessage());
		}
    }
}

