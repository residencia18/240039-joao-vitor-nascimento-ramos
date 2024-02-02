package brc.om.devdojo;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class IteratorTest {
	
	public static void main(String[] args) {
		List<Manga> mangas = new LinkedList();
		
		mangas.add(new Manga(1L,"Pokemon",15.8,7));
        mangas.add(new Manga(2L, "Naruto", 9.5,6));
        mangas.add(new Manga(3L, "One Piece", 9.8,5));
        mangas.add(new Manga(4L, "Dragon Ball", 8.7,11));
        mangas.add(new Manga(5L, "Attack on Titan", 9.9,0));
        
//        Iterator<Manga> iterator = mangas.iterator();
//        
//        while(iterator.hasNext()) {
//        	Manga manga = iterator.next();
//        	if(manga.quantidade==0) {
//        		iterator.remove();
//        	}
//        }
//        
        
        mangas.removeIf(manga -> manga.quantidade==0);
        System.out.println(mangas);

	}

}
