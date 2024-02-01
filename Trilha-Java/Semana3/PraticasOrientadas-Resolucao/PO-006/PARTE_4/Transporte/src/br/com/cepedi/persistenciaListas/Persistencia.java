package br.com.cepedi.persistenciaListas;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public abstract class Persistencia {

    public static <T> void salvarEmArquivo(T obj, String nomeArquivo) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nomeArquivo))) {
            oos.writeObject(obj);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erro ao salvar o objeto no arquivo: " + nomeArquivo);
        }
    }
    
    public static <T> T lerDoArquivo(String nomeArquivo) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nomeArquivo))) {
            // Tenta ler o objeto do arquivo
            @SuppressWarnings("unchecked")
            T objetoLido = (T) ois.readObject();
            return objetoLido;
        } catch (EOFException e) {
            // Trata EOFException (arquivo vazio)
            System.out.println("O arquivo está vazio: " + nomeArquivo);
            return null;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Erro ao ler o objeto do arquivo: " + nomeArquivo);
            return null;
        }
    }


}
