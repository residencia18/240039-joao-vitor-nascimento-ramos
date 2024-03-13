package br.com.cepedi.petshop.controller.DTO;

import br.com.cepedi.petshop.model.Marca;

public class MarcaDTO {

    private String nome;


    public MarcaDTO(Marca marca) {
        this.nome = marca.getNome();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
