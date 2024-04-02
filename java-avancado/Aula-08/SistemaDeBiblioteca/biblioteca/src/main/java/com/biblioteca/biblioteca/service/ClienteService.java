package com.biblioteca.biblioteca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biblioteca.biblioteca.Exceptions.CPFInvalidoException;
import com.biblioteca.biblioteca.Exceptions.NomeInvalidoException;
import com.biblioteca.biblioteca.controller.repository.ClienteRepository;
import com.biblioteca.biblioteca.model.Cliente;
import com.biblioteca.biblioteca.faker.PtBRCpfIdNumber; // Importe a classe PtBRCpfIdNumber
import com.github.javafaker.Faker;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    private Faker faker = new Faker();
    private PtBRCpfIdNumber cpfGenerator = new PtBRCpfIdNumber(); // Instancie PtBRCpfIdNumber

    public void popularClientes(int quantidade) throws NomeInvalidoException, CPFInvalidoException {
        for (int i = 0; i < quantidade; i++) {
            Cliente cliente = new Cliente();
            cliente.setNome(faker.name().fullName());
            cliente.setCpf(gerarCPF());
            cliente.setEmail(faker.internet().emailAddress());
            clienteRepository.save(cliente);
        }
    }
    
    // Método para gerar CPF usando PtBRCpfIdNumber
    private String gerarCPF() {
        return cpfGenerator.getValidFormattedCpf(faker); // Utilize o método getValidFormattedCpf() para gerar um CPF válido formatado
    }
    
    public Cliente obterAleatorio() {
        long total = clienteRepository.count();
        int indiceAleatorio = faker.number().numberBetween(0, (int) total);
        return clienteRepository.findAll().get(indiceAleatorio);
    }
}
