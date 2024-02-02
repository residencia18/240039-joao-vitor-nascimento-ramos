package br.com.cepedi;

import java.util.Comparator;
import java.util.NavigableSet;
import java.util.TreeSet;

class comparatorPrice implements Comparator<Manga>{

	@Override
	public int compare(Manga o1, Manga o2) {
		return Double.compare(o1.getPreco(), o2.getPreco());
		
	}
	
	
	
}

public class NavigableTest2 {
	
	public static void main(String[] args) {
		
		NavigableSet<Manga> mangas = new TreeSet<>(new comparatorPrice());
		
		
		mangas.add(new Manga(1L,"Pokemon",15.8,7));
        mangas.add(new Manga(2L, "Naruto", 9.5,6));
        mangas.add(new Manga(3L, "One Piece", 9.8,5));
        mangas.add(new Manga(4L, "Dragon Ball", 8.7,11));
        mangas.add(new Manga(5L, "Attack on Titan", 9.9,0));
        mangas.add(new Manga(5L, "Attack on Titan", 9.9,0));

		for(Manga m : mangas) {
			System.out.println(m);
		}
		
		System.out.println("------------------");
		//imediato menor 
		System.out.println(mangas.lower(new Manga(5L, "Attack on Titan", 9.9,0)));
		// menor igual
		System.out.println(mangas.floor(new Manga(5L, "Attack on Titan", 9.9,0)));
		//imediato maior
		System.out.println(mangas.higher(new Manga(5L, "Attack on Titan", 9.9,0)));
		// maior igual
		System.out.println(mangas.ceiling(new Manga(5L, "Attack on Titan", 9.9,0)));
		System.out.println("------------------");

		System.out.println(mangas.size());
		System.out.println(mangas.pollFirst());
		System.out.println(mangas.size());

	}

}
