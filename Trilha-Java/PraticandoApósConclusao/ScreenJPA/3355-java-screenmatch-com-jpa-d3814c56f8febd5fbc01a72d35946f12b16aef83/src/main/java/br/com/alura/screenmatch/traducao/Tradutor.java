package br.com.alura.screenmatch.traducao;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Tradutor {
    public static String traduzirInglesParaPortugues(String text) {
        ObjectMapper mapper = new ObjectMapper();

        String json = MyMemoryApiConnector.get(text, Linguagem.INGLES, Linguagem.PORTUGUES);

        MyMemoryResponseValue traducao;
        try {
            traducao = mapper.readValue(json, MyMemoryResponseValue.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        traducao.dadosResposta.textoTraduzido = URLDecoder.decode(traducao.dadosResposta.textoTraduzido, StandardCharsets.UTF_8);

        return traducao.dadosResposta.textoTraduzido;
    }
}