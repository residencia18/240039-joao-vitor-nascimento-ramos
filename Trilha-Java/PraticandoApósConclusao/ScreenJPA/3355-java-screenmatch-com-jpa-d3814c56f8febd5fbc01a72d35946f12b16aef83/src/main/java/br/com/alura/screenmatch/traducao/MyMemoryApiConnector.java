package br.com.alura.screenmatch.traducao;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class MyMemoryApiConnector {
    public static String get(String texto, Linguagem lingua1, Linguagem lingua2) {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(MyMemoryURLGenerator.urlEncodeQuery(texto, lingua1, lingua2)))
                .build();

        try {
            HttpResponse<String> resposta = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (resposta.statusCode() == 200) {
                return resposta.body();
            } else {
                throw new IOException("Resposta mal-sucedida da MyMemory API");
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}