package br.com.cepedi.bs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BinarySearchTest {
	
	public static void main(String[] args) {
		List<Manga> mangas = new ArrayList();
		
		mangas.add(new Manga(1L,"Pokemon",15.8));
        mangas.add(new Manga(2L, "Naruto", 9.5));
        mangas.add(new Manga(3L, "One Piece", 9.8));
        mangas.add(new Manga(4L, "Dragon Ball", 8.7));
        mangas.add(new Manga(5L, "Attack on Titan", 9.9));
        
        Collections.sort(mangas);
        System.out.println(Collections.binarySearch(mangas,new Manga(4L, "Dragon Ball", 8.7) ));
        for(Manga m : mangas) {
        	System.out.println(m);
        }
        
	}

}
