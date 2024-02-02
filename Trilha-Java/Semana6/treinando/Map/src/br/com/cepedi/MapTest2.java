package br.com.cepedi;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapTest2 {
	public static void main(String[] args) {
		Consumidor consumidor1 = new Consumidor("João");
		Consumidor consumidor2 = new Consumidor("Paulo");
		Manga m1 = new Manga(1L,"Pokemon",15.8,7);
        Manga m2 = new Manga(2L, "Naruto", 9.5,6);
        Manga m3 = new Manga(3L, "One Piece", 9.8,5);
        Manga m4 = new Manga(4L, "Dragon Ball", 8.7,11);
        Manga m5 = new Manga(5L, "Attack on Titan", 9.9,0);

        
        Map<Consumidor,List<Manga>> consumidorManga = new HashMap<>();
        List<Manga> mangaList = List.of(m1,m2,m3);
        List<Manga> mangaList2 = List.of(m1,m5,m3);
        consumidorManga.put(consumidor1, mangaList);
        consumidorManga.put(consumidor2, mangaList2);
        
        for(Map.Entry<Consumidor,List<Manga>> entry : consumidorManga.entrySet()) {
        	System.out.println(entry.getKey().getNome() + " - " + entry.getValue());
        }
	}
}
