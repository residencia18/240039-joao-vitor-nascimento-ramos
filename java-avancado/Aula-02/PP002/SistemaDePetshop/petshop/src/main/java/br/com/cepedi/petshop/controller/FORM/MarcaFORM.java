package br.com.cepedi.petshop.controller.FORM;

import br.com.cepedi.petshop.model.Marca;

public class MarcaFORM {

    private String nome;

    // Getters e Setters

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Marca toMarca() {
        Marca marca = new Marca();
        marca.setNome(this.nome);
        return marca;
    }
}
