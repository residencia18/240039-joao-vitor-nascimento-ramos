package br.com.cepedi.arquivo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class arquivos1 {
    public static void main(String[] args) {
        // Caminho do arquivo a ser lido
        String caminhoArquivo = "./arquivo/entrada.txt";
        File arquivo = new File(caminhoArquivo);

        if (!arquivo.exists()) {
            System.out.println("Arquivo não encontrado: " + caminhoArquivo);
            return;
        }

        try (BufferedReader leitor = new BufferedReader(new FileReader(arquivo))) {
            String linha;

            // Lê cada linha do arquivo e exibe no console
            while ((linha = leitor.readLine()) != null) {
                System.out.println(linha);
            }
        } catch (IOException e) {
            // Trata exceções de leitura do arquivo
            e.printStackTrace();
        }
    }
}

