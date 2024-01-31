package br.com.cepedi.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class MangaByIdComparator implements Comparator<Manga>{

	@Override
	public int compare(Manga o1, Manga o2) {
		if(o1.getId() == o2.getId()) {
			return 0;
		}else if (o1.getId() > o2.getId()) {
			return 1;
		}
		return -1;

	}

	
	
}

public class SortList {
	
	public static void main(String[] args)  {
		List<Manga> mangas = new ArrayList();
		
		mangas.add(new Manga(1L,"Pokemon",15.8));
        mangas.add(new Manga(2L, "Naruto", 9.5));
        mangas.add(new Manga(3L, "One Piece", 9.8));
        mangas.add(new Manga(4L, "Dragon Ball", 8.7));
        mangas.add(new Manga(5L, "Attack on Titan", 9.9));
        Collections.sort(mangas);
        for(Manga m : mangas) {
        	System.out.println(m);
        }
        System.out.println("----------------------");
        mangas.sort(new MangaByIdComparator());
        for(Manga m : mangas) {
        	System.out.println(m);
        }
	}

}
