package br.com.cepedi.arquivo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class arquivos3 {
	
	public static void main(String[] args) {
		
        String caminhoOrigem = "./arquivo/origem.txt";
        String caminhoDestino = "./arquivo/destino.txt";
        File origem = new File(caminhoOrigem);
        File destino = new File(caminhoDestino);

        try (BufferedReader leitor = new BufferedReader(new FileReader(origem));
        		BufferedWriter writer = new BufferedWriter(new FileWriter(destino))
        		) {
            String linha;
            while ((linha = leitor.readLine() ) != null) {
                writer.write(linha);
                writer.newLine();
            }

            System.out.println("Linhas gravadas com sucesso no arquivo 'destino.txt'");
        } catch (IOException e) {
            System.err.println("Erro ao gravar no arquivo.");
            e.printStackTrace();
        }
	}

}
