package br.com.alura.screesound.traducao;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class MyMemoryURLGenerator {
    public static String urlEncodeQuery(String text, Linguagem lingua1, Linguagem lingua2) {
        String texto = URLEncoder.encode(text, StandardCharsets.UTF_8);
        String langpair = URLEncoder.encode(lingua1.siglaLinguagem+"|"+lingua2.siglaLinguagem, StandardCharsets.UTF_8);

        String url = "https://api.mymemory.translated.net/get?q="+texto+"&langpair="+langpair;

        return url;
    }
}