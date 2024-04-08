package br.com.alura.screesound.traducao;

import java.util.NoSuchElementException;

public enum Linguagem {
    PORTUGUES("pt"),
    INGLES("en");

    public String siglaLinguagem;

    Linguagem(String lang) {
        this.siglaLinguagem = lang;
    }

    public static Linguagem getLang(String lang) {
        for (Linguagem self : Linguagem.values()) {
            if (self.siglaLinguagem.equalsIgnoreCase(lang)) {
                return self;
            }
        }
        throw new NoSuchElementException("Língua não encontrada");
    }
}