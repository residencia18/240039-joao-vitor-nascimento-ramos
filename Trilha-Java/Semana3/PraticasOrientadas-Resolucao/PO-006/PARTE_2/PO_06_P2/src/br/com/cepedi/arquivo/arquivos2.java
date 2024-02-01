package br.com.cepedi.arquivo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class arquivos2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite as linhas de texto (Digite 'sair' para encerrar):");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("./arquivo/saida.txt"))) {
            String linha;
            while (!(linha = scanner.nextLine()).equalsIgnoreCase("sair")) {
                writer.write(linha);
                writer.newLine();
            }

            System.out.println("Linhas gravadas com sucesso no arquivo 'saida.txt'");
        } catch (IOException e) {
            System.err.println("Erro ao gravar no arquivo.");
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}
